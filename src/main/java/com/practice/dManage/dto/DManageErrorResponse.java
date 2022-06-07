package com.practice.dManage.dto;

import com.practice.dManage.exception.DManageErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DManageErrorResponse {
    private DManageErrorCode errorCode;
    private String errorMessage;
}
