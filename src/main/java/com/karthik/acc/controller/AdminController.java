package com.karthik.acc.controller;

import com.karthik.acc.entity.UserRequest;
import com.karthik.acc.model.SubmissionDto;
import com.karthik.acc.model.UserRequestDto;
import com.karthik.acc.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRequestService userRequestService;

    @RequestMapping(value = "/submitValidation")
    public void submitValidation(@RequestBody SubmissionDto submissionDto) {
        userRequestService.submitAdminValidation(submissionDto);
    }
}
