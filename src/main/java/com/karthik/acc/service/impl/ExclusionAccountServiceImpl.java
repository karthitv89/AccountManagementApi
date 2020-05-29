package com.karthik.acc.service.impl;

import com.karthik.acc.entity.ExclusionAccount;
import com.karthik.acc.repository.ExclusionAccountRepository;
import com.karthik.acc.service.ExclusionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("exclusionAccountService")
public class ExclusionAccountServiceImpl extends  BaseServiceImpl<ExclusionAccount, String> implements ExclusionAccountService {


    @Autowired
    private ExclusionAccountRepository exclusionAccountRepository;


    @Override
    public CrudRepository<ExclusionAccount, String> getRepository() {
        return exclusionAccountRepository;
    }

    @Override
    public List<String> listAllAsStringList() {
        List<ExclusionAccount> allEA = this.listAll();
        return allEA.stream().map(acc -> acc.getAccountNumber()).collect(Collectors.toList());
    }
}
