package com.practice.dManage.service;

import com.practice.dManage.code.StatusCode;
import com.practice.dManage.dto.CreateDeveloper;
import com.practice.dManage.dto.DeveloperDetailDto;
import com.practice.dManage.dto.DeveloperDto;
import com.practice.dManage.dto.EditDeveloper;
import com.practice.dManage.entity.Developer;
import com.practice.dManage.entity.RetiredDeveloper;
import com.practice.dManage.exception.DManageException;
import com.practice.dManage.repository.DeveloperRepository;
import com.practice.dManage.repository.RetiredDeveloperRepository;
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
    private final RetiredDeveloperRepository retiredDeveloperRepository;


    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .statusCode(StatusCode.EMPLOYED)
                .name(request.getName())
                .age(request.getAge())
                .build();

        developerRepository.save(developer);

        return CreateDeveloper.Response.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
        validateDeveloperLevel(
                request.getDeveloperLevel(),
                request.getExperienceYears());
        developerRepository.findByMemberId(request.getMemberId()).ifPresent((developer) -> {
            throw new DManageException(DUPLICATED_MEMBER_ID);
        });
    }

    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository
                .findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DManageException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateEditDeveloperRequest(request, memberId);
        Developer developer = developerRepository.findByMemberId(memberId).orElseThrow(
                () -> new DManageException(NO_DEVELOPER)
        );

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateEditDeveloperRequest(
            EditDeveloper.Request request,
            String memberId) {
        validateDeveloperLevel(
                request.getDeveloperLevel(),
                request.getExperienceYears()
        );

    }

    private void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYears) {
        if (developerLevel == DeveloperLevel.SENIOR && experienceYears < 10) {
            throw new DManageException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.NEW && experienceYears > 0) {
            throw new DManageException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4) {
            throw new DManageException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(()-> new DManageException(NO_DEVELOPER));
        developer.setStatusCode(StatusCode.RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();
        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }
}
