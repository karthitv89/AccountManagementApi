package com.karthik.acc.service;

import com.karthik.acc.entity.ExclusionAccount;

import java.util.List;

public interface ExclusionAccountService extends  BaseService<ExclusionAccount,String> {
    List<String> listAllAsStringList() ;
}
