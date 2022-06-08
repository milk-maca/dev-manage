package com.practice.dManage.controller;

import com.practice.dManage.dto.*;
import com.practice.dManage.exception.DManageException;
import com.practice.dManage.service.DManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
public class DManageController {
    private final DManageService dManageService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");

        return dManageService.getAllEmployedDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(
            @PathVariable String memberId) {
        log.info("GET /developers HTTP/1.1");

        return dManageService.getDeveloperDetail(memberId);
    }

    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request) {
        log.info("request : {}", request);

        return dManageService.createDeveloper(request);
    }

    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request

    ) {
        log.info("PUT/developers HTTP/1.1");

        return dManageService.editDeveloper(memberId, request);
    }

    @DeleteMapping("/developer/{memberId}")
    public DeveloperDetailDto deleteDeveloper(
            @PathVariable String memberId
    ) {
        return dManageService.deleteDeveloper(memberId);
    }


}

