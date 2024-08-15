package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountPayload {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("AccountType")
    private String accountType;

    @JsonProperty("FullyQualifiedName")
    private String fullyQualifiedName;

    private String domain;

    @JsonProperty("SubAccount")
    private boolean subAccount;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Classification")
    private String classification;

    @JsonProperty("AccountSubType")
    private String accountSubType;

    @JsonProperty("CurrentBalanceWithSubAccounts")
    private int currentBalanceWithSubAccounts;


    private boolean sparse;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class MetaData {

        @JsonProperty("CreateTime")
        private String createTime;

        @JsonProperty("LastUpdatedTime")
        private String lastUpdatedTime;
    }

    @JsonProperty("CurrentBalance")
    private int currentBalance;

    @JsonProperty("Active")
    private boolean active;

    @JsonProperty("SyncToken")
    private String syncToken;

    @JsonProperty("Id")
    private String id;
}

