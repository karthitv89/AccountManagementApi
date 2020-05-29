package com.karthik.acc.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Setter
@Getter
public class UserRequestDto {
    private UUID requestId;

    private String submittedBy;

    private Date submittedDate;

    private String approvedBy;

    private Date approvedDate;

    private String status;

    private String accountNumber;
}
