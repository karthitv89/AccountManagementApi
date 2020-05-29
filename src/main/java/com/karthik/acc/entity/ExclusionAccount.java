package com.karthik.acc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EXCLUSION_ACCOUNTS")
@Data
@NoArgsConstructor
public class ExclusionAccount {


    @Id
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    public ExclusionAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
