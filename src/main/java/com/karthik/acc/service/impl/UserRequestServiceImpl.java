package com.karthik.acc.service.impl;

import com.karthik.acc.common.AppConstants;
import com.karthik.acc.entity.ExclusionAccount;
import com.karthik.acc.entity.UserRequest;
import com.karthik.acc.model.SearchDto;
import com.karthik.acc.model.SubmissionDto;
import com.karthik.acc.repository.UserRequestRepository;
import com.karthik.acc.service.ExclusionAccountService;
import com.karthik.acc.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("userRequestService")
public class UserRequestServiceImpl extends BaseServiceImpl<UserRequest,UUID> implements UserRequestService {

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private ExclusionAccountService exclusionAccountService;

    @Override
    protected void preInsert(UserRequest userRequest) {
        super.preInsert(userRequest);
        userRequest.setRequestId(UUID.randomUUID());
        userRequest.setSubmittedDate(new Date());
        userRequest.setStatus(AppConstants.USER_REQUEST_STATUS_PENDING);
    }



    @Override
    public SearchDto<UserRequest> search(SearchDto searchDto) {
        SearchDto<UserRequest> results = new SearchDto<>();
        List<UserRequest> userRequestList = new ArrayList<>();
        if (searchDto.isExclusionAccounts()) {
            List<String> excAccNumbers =  exclusionAccountService.listAllAsStringList();
            userRequestList = userRequestRepository.fetchUserRequestsByExclusionAccounts(excAccNumbers);
        } else if(searchDto.isPendingAccounts()) {
            userRequestList = userRequestRepository.fetchByStatus(AppConstants.USER_REQUEST_STATUS_PENDING);
        } /*else {

        }*/
        results.setSearchResults(userRequestList);
        return  results;
    }

    @Override
    public CrudRepository<UserRequest, UUID> getRepository() {
        return userRequestRepository;
    }

    @Override
    public void submitAdminValidation(SubmissionDto submissionDto) {
        Date approvedDate = new Date();
        List<UserRequest> userRequestList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(submissionDto.getRejectedRequests())) {
            List<UserRequest> rejected = (List<UserRequest>) userRequestRepository.findAllById(submissionDto.getRejectedRequests().stream().map(s -> UUID.fromString(s)).collect(Collectors.toList()));
            rejected.stream().forEach(userRequest -> {
                userRequest.setStatus(AppConstants.USER_REQUEST_STATUS_REJECTED);
                userRequestList.add(userRequest);
            });
        }
        if (!CollectionUtils.isEmpty(submissionDto.getApprovedRequests())) {
            List<UserRequest> approved = (List<UserRequest>) userRequestRepository.findAllById(
                    submissionDto.getApprovedRequests().stream().map(s -> UUID.fromString(s)).collect(Collectors.toList()));
            approved.stream().forEach(userRequest -> {
                userRequest.setStatus(AppConstants.USER_REQUEST_STATUS_APPROVED);
                userRequestList.add(userRequest);
            });
        }

        userRequestList.stream().forEach(userRequest -> {
            userRequest.setApprovedDate(approvedDate);
            userRequest.setApprovedBy(submissionDto.getUsername());
        });
        userRequestRepository.saveAll(userRequestList);


        exclusionAccountService.createAll(retrieveExclusionAccounts(userRequestList));
    }

    @Override
    public void cancelUserRequests(List<UUID> cancelIds, String username) {
        List<UserRequest> userRequests = (List<UserRequest>) userRequestRepository.findAllById(cancelIds);
        userRequests.stream().forEach(ur -> {
            ur.setStatus(AppConstants.USER_REQUEST_STATUS_CANCELED);
            ur.setSubmittedDate(new Date());
            ur.setSubmittedBy(username);
        });
        userRequestRepository.saveAll(userRequests);

    }

   /* @Override
    public void bulkCreate(List<String> accountIds) {
        List<UserRequest> userRequestList = accountIds.stream().map( acc-> new UserRequest()).collect(Collectors.toList());

        this.createAll(userRequestList);
    }

    private UserRequest populateUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setStatus(AppConstants._USER_REQUEST_STATUS_PENDING);
        userRequest.setSubmittedBy();
    }*/

    private List<ExclusionAccount> retrieveExclusionAccounts(List<UserRequest> userRequests) {
        return userRequests.stream().map(ur -> new ExclusionAccount(ur.getAccountNumber()))
                .collect(Collectors.toList());
    }
}
