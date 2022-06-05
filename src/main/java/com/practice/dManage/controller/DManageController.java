package com.practice.dManage.controller;

import com.practice.dManage.dto.CreateDeveloper;
import com.practice.dManage.dto.DeveloperDetailDto;
import com.practice.dManage.dto.DeveloperDto;
import com.practice.dManage.service.DManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

        return dManageService.getAllDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(@PathVariable String memberId) {
        log.info("GET /developers HTTP/1.1");

        return dManageService.getDeveloperDetail(memberId);
    }

    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(@Valid @RequestBody CreateDeveloper.Request request) {
        log.info("request : {}", request);

        return dManageService.createDeveloper(request);
    }
}

