package com.karthik.acc.repository;

import com.karthik.acc.entity.UserRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("userRequestRepository")
public interface UserRequestRepository extends CrudRepository<UserRequest, UUID> {
    @Query("SELECT UR FROM UserRequest UR WHERE UR.accountNumber IN (:exclusionAccounts)")
    List<UserRequest> fetchUserRequestsByExclusionAccounts(@Param("exclusionAccounts") List<String> exclusionAccounts);

    @Query("SELECT UR FROM UserRequest UR WHERE UR.status = :status")
    List<UserRequest> fetchByStatus(String status);

}
