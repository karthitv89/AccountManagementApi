package com.karthik.acc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "USER_REQUEST")
@Data
@NoArgsConstructor
public class UserRequest {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "request_id", columnDefinition = "BINARY(16)")
    private UUID requestId;

    @Column(name = "submitted_by")
    private String submittedBy;

    @Column(name = "submitted_date")
    private Date submittedDate;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_date")
    private Date approvedDate;

    @Column(name = "status")
    private String status;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    public UserRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
