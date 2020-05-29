package com.karthik.acc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class SearchDto<T> {
    @JsonProperty("isExclusionAccounts")
    private boolean isExclusionAccounts;

    @JsonProperty("isPendingAccounts")
    private boolean isPendingAccounts;

    private List<T> searchResults = new ArrayList<>();
    private Integer count;
    private Integer pageSize;
    private Integer pageNumber;

    /*public boolean isExclusionAccounts() {
        return isExclusionAccounts;
    }

    public void setExclusionAccounts(boolean exclusionAccounts) {
        isExclusionAccounts = exclusionAccounts;
    }

    public boolean isPendingAccounts() {
        return isPendingAccounts;
    }

    public void setPendingAccounts(boolean pendingAccounts) {
        isPendingAccounts = pendingAccounts;
    }

    @JsonProperty("isExclusionAccounts")
    public boolean getIsExclusionAccounts() {
        return this.isExclusionAccounts;
    }

    @JsonProperty("isPendingAccounts")
    public boolean getIsPendingAccounts() {
        return  this.isPendingAccounts;
    }*/
}
