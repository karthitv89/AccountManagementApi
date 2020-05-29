package com.karthik.acc.repository;

import com.karthik.acc.entity.ExclusionAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("exclusionAccountRepository")
public interface ExclusionAccountRepository extends CrudRepository<ExclusionAccount, String> {
}
