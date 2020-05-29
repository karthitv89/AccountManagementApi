package com.karthik.acc.service;

import com.karthik.acc.entity.UserRequest;
import com.karthik.acc.model.SubmissionDto;

import java.util.List;
import java.util.UUID;

public interface UserRequestService extends BaseService<UserRequest, UUID> {
    void submitAdminValidation(SubmissionDto submissionDto);

    void cancelUserRequests(List<UUID> cancelIds, String username);

    /// void bulkCreate(List<String> accountIds);
}
