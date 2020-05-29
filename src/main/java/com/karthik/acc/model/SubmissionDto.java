package com.karthik.acc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter @Setter
public class SubmissionDto {
    @JsonProperty("approvedRequests")
    private List<String> approvedRequests = new ArrayList<>();

    @JsonProperty("rejectedRequests")
    private List<String> rejectedRequests = new ArrayList<>();

    private String username;
}
