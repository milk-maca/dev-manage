package com.practice.dManage.controller;

import com.practice.dManage.dto.CreateDeveloper;
import com.practice.dManage.service.DManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DManageController {
    private final DManageService dMakerService;

    @GetMapping("/developers")
    public List<String> getAllDevelopers() {
        // GET /developers HTTP/1.1
        log.info("GET /developers HTTP/1.1");

        return Arrays.asList("1", "2", "3");
    }

    @PostMapping("/create-developers")
    public CreateDeveloper.Response createDevelopers(@Valid @RequestBody CreateDeveloper.Request request
    ) {
        log.info("request : {}", request);

        return dMakerService.createDeveloper(request);
    }
}
