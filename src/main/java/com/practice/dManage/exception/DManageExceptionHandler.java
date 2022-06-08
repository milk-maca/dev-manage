package com.practice.dManage.exception;

import com.practice.dManage.dto.DManageErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.practice.dManage.exception.DManageErrorCode.INTERNAL_SERVER_ERROR;
import static com.practice.dManage.exception.DManageErrorCode.INVALID_REQUEST;

@Slf4j
@RestControllerAdvice
public class DManageExceptionHandler {

    @ExceptionHandler(DManageException.class)
    public DManageErrorResponse handleException(
            DManageException e,
            HttpServletRequest request
    ){
        log.error("errorCode: {}, url: {}, message:{}",
                e.getDManageErrorCode(),
                request.getRequestURI(),
                e.getDetailMessage()
        );
        return DManageErrorResponse.builder()
                .errorCode(e.getDManageErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,   //create 메서드에 Post 가 아닌 다른 메서드를 요청 할경우
            MethodArgumentNotValidException.class           //@NotNull 등 에서 문제가 발생했을 때
    })
    public DManageErrorResponse handleBadRequest(
            Exception e, HttpServletRequest request
    ) {
        log.error("url: {}, message:{}",
                request.getRequestURI(),
                e.getMessage()
        );
        return DManageErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

    //고려되지 않은 에러
    @ExceptionHandler(Exception.class)
    public DManageErrorResponse handleException(
            Exception e, HttpServletRequest request
    ) {
        log.error("url: {}, message:{}",
                request.getRequestURI(),
                e.getMessage()
        );
        return DManageErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
