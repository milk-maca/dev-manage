package com.practice.dManage.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeveloperLevel {
    NEW("신입"),
    JUNIOR("주니어"),
    SENIOR("시니어");

    private final String description;
}
