package com.karthik.acc.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Setter @Getter
public class ExclusionAccountDto {

    private String accountNumber;

}
