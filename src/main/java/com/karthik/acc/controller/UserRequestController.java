package com.karthik.acc.controller;

import com.karthik.acc.common.AppConstants;
import com.karthik.acc.entity.UserRequest;
import com.karthik.acc.model.SearchDto;
import com.karthik.acc.model.UserRequestDto;
import com.karthik.acc.model.converters.UserRequestConverter;
import com.karthik.acc.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/userRequest")
public class UserRequestController {

    @Autowired
    private UserRequestService userRequestService;

    @PostMapping(value = "/create")
    public void create(@RequestBody UserRequest userRequest) {
        userRequestService.create(userRequest);
    }

    @PostMapping(value = "/bulkCreate")
    public void create(@RequestBody List<UserRequestDto> userRequestDtoList) {
        userRequestService.createAll(UserRequestConverter.convertToEntityList(userRequestDtoList));
       // userRequestService.createAll(populateUserRequests(userRequestList));
    }

    private List<UserRequest> populateUserRequests(List<String> userRequestList) {
        List<UserRequest> userRequests = new ArrayList<>();
        userRequestList.stream().forEach( acc-> {
            UserRequest userRequest = new UserRequest();
            userRequest.setAccountNumber(acc);
            userRequest.setStatus(AppConstants.USER_REQUEST_STATUS_PENDING);
            userRequest.setSubmittedDate(new Date());
            userRequest.setSubmittedBy("Tom");//TODO hard coded
            userRequests.add(userRequest);
        });
        return userRequests;
    }

    @PostMapping(value="/cancel/{username}")
    public void cancelUserRequests(@RequestBody List<UUID> cancelIds, @PathVariable String username) {
        userRequestService.cancelUserRequests(cancelIds, username);
    }


    @PutMapping(value = "/update/{userRequestUUID}")
    public UserRequest updateUserRequest(@RequestBody UserRequest userRequest, @PathVariable UUID uuid) {
        userRequest.setRequestId(uuid);
        return userRequestService.update(userRequest);
    }

    @DeleteMapping("/delete/{uuid}")
    public boolean deleteUserRequest(@PathVariable UUID uuid) {

        userRequestService.deleteByUUID(uuid);
        return false;
    }


    @GetMapping(value = "/listAllForApproval")
    public List<UserRequestDto> listAllForApproval() {
        SearchDto<UserRequest> searchDto = new SearchDto<>();
        searchDto.setPendingAccounts(true);
        searchDto = userRequestService.search(searchDto);
        List<UserRequestDto> userRequestDtoList = new ArrayList<>();
        searchDto.getSearchResults().forEach(ur -> {
            UserRequestDto userRequestDto = UserRequestConverter.convertToDto(ur);
            userRequestDtoList.add(userRequestDto);
        });
        return userRequestDtoList;
    }


    @GetMapping
    public List<UserRequestDto> listAll() {
        List<UserRequestDto> userRequestDtoList = new ArrayList<>();
        userRequestService.listAll().forEach(ur -> {
            UserRequestDto userRequestDto = UserRequestConverter.convertToDto(ur);
            userRequestDtoList.add(userRequestDto);
        });
        return userRequestDtoList;
    }

    @PostMapping(value = "/search")
    public SearchDto<UserRequestDto> search(@RequestBody SearchDto searchDto) {
        SearchDto<UserRequestDto> results = new SearchDto<>();
        List<UserRequestDto> userRequestDtoList = new ArrayList<>();

        userRequestService.search(searchDto).getSearchResults().forEach( ur -> {
            UserRequestDto userRequestDto = UserRequestConverter.convertToDto(ur);
            userRequestDtoList.add(userRequestDto);
        });
        results.setSearchResults(userRequestDtoList);
        return results;

    }


}
