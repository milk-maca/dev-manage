package com.practice.dManage.type;


        import lombok.AllArgsConstructor;
        import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeveloperSkillType {
    FE("프론트엔드"),
    BE("백엔드"),
    FULL_STACK("풀스택");

    private final String description;
}
