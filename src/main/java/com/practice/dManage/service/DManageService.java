package com.practice.dManage.service;

import com.practice.dManage.dto.CreateDeveloper;
import com.practice.dManage.entity.Developer;
import com.practice.dManage.exception.DManageException;
import com.practice.dManage.repository.DeveloperRepository;
import com.practice.dManage.type.DeveloperLevel;
import com.practice.dManage.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

import java.util.Optional;

import static com.practice.dManage.exception.DManageErrorCode.DUPLICATED_MEMBER_ID;
import static com.practice.dManage.exception.DManageErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;

@Service
@RequiredArgsConstructor
public class DManageService {
    private final DeveloperRepository developerRepository;


    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder().developerLevel(request.getDeveloperLevel()).developerSkillType(request.getDeveloperSkillType()).experienceYears(request.getExperienceYears()).name(request.getName()).age(request.getAge()).build();

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
}
