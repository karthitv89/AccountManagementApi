package com.karthik.acc.controller;

import com.karthik.acc.entity.ExclusionAccount;
import com.karthik.acc.service.ExclusionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exclusionAccount")
public class ExclusionAccountController {
    @Autowired
    private ExclusionAccountService exclusionAccountService;

    @PostMapping(value = "/create")
    public void create(@RequestBody ExclusionAccount exclusionAccount) {
        exclusionAccountService.create(exclusionAccount);
    }

    @PostMapping(value = "/bulkCreate")
    public void create(@RequestBody List<ExclusionAccount> exclusionAccounts) {
        exclusionAccountService.createAll(exclusionAccounts);
    }


}
