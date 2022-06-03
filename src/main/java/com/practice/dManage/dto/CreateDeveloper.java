package com.practice.dManage.dto;

import com.practice.dManage.entity.Developer;
import com.practice.dManage.type.DeveloperLevel;
import com.practice.dManage.type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDeveloper {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        @NotNull
        private DeveloperLevel developerLevel;

        @NotNull
        private DeveloperSkillType developerSkillType;

        @NotNull
        @Min(0)
        @Max(20)
        private Integer experienceYears;

        @NotNull
        @Size(min = 3, max = 50, message = "size~50")
        private String memberId;

        @NotNull
        @Size(min = 3, max = 20, message = "size 3~20")
        private String name;

        @NotNull
        @Min(18)
        private Integer age;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private DeveloperLevel developerLevel;
        private DeveloperSkillType developerSkillType;
        private Integer experienceYears;
        private String memberId;

        public static Response fromEntity(Developer developer){
            return Response.builder()
                    .developerLevel(developer.getDeveloperLevel())
                    .developerSkillType(developer.getDeveloperSkillType())
                    .experienceYears(developer.getExperienceYears())
                    .memberId(developer.getMemberId())
                    .build();



        }
    }
}
