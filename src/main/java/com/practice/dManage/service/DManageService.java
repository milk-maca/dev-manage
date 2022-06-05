package com.practice.dManage.service;

import com.practice.dManage.dto.CreateDeveloper;
import com.practice.dManage.dto.DeveloperDetailDto;
import com.practice.dManage.dto.DeveloperDto;
import com.practice.dManage.entity.Developer;
import com.practice.dManage.exception.DManageException;
import com.practice.dManage.service.repository.DeveloperRepository;
import com.practice.dManage.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.practice.dManage.exception.DManageErrorCode.*;

@Service
@RequiredArgsConstructor
public class DManageService {
    private final DeveloperRepository developerRepository;


    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .name(request.getName())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);

        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYears = request.getExperienceYears();
        if (developerLevel == DeveloperLevel.SENIOR && experienceYears < 10) {
            throw new DManageException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        if (developerLevel == DeveloperLevel.NEW && experienceYears > 0) {
            throw new DManageException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4) {
            throw new DManageException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        developerRepository.findByMemberId(request.getMemberId()).ifPresent((developer) -> {
            throw new DManageException(DUPLICATED_MEMBER_ID);
        });


    }

    public List<DeveloperDto> getAllDevelopers() {
        return developerRepository.findAll()
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DManageException(NO_DEVELOPER));
    }
}
