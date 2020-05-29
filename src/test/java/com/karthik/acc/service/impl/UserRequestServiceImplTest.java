package com.karthik.acc.service.impl;

import com.karthik.acc.common.AppConstants;
import com.karthik.acc.entity.UserRequest;
import com.karthik.acc.repository.UserRequestRepository;
import com.karthik.acc.service.UserRequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestServiceImplTest {
    @Autowired
    private UserRequestService userRequestService;


    @Autowired
    private UserRequestRepository userRequestRepository;

    private UserRequest createUserRequest(String accNumber) {
        UserRequest userRequest = new UserRequest(accNumber);
        userRequest.setSubmittedDate(new Date());
        userRequest.setSubmittedBy("Tom");
        userRequest.setStatus(AppConstants.USER_REQUEST_STATUS_PENDING);
        return userRequest;
    }

    @Test
    void search() {
    }

    @Test
    void submitAdminValidation() {
    }

    @Test
    void cancelUserRequests() {
    }




    @Test
    public void contextLoads() {

    }

    @Test
    void create() {
       // userRequestService.create(createUserRequest("2113123123"));
    }

    @Test
    void createAll() {
    }

    @Test
    void preUpdate() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteByUUID() {
    }

    @Test
    void listAll() {
    }


}