package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponse {

    @JsonProperty("FullyQualifiedName")
    private String fullyQualifiedName;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Classification")
    private String classification;

    @JsonProperty("AccountSubType")
    private String accountSubType;

    @JsonProperty("CurrencyRef")
    private CurrencyRef currencyRef;

    @JsonProperty("CurrentBalanceWithSubAccounts")
    private int currentBalanceWithSubAccounts;

    @JsonProperty("sparse")
    private boolean sparse;

    @JsonProperty("MetaData")
    private MetaData metaData;

    @JsonProperty("AccountType")
    private String accountType;

    @JsonProperty("CurrentBalance")
    private long currentBalance;

    @JsonProperty("Active")
    private boolean Active;

    @JsonProperty("SyncToken")
    private String syncToken;

    @JsonProperty("Id")
    private String id;

    @JsonProperty("SubAccount")
    private boolean subAccount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CurrencyRef {

        @JsonProperty("name")
        private String name;

        @JsonProperty("value")
        private String value;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class MetaData {

        @JsonProperty("CreateTime")
        private String createTime;

        @JsonProperty("LastUpdatedTime")
        private String lastUpdatedTime;
    }
}
