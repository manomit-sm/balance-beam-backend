package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quickbook.playground.bo.CustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResponse {

    @JsonProperty("FullyQualifiedName")
    private String FullyQualifiedName;

    @JsonProperty("PrimaryEmailAddr")
    private CustomerRequest.PrimaryEmailAddr PrimaryEmailAddr;

    @JsonProperty("DisplayName")
    private String DisplayName;

    @JsonProperty("Suffix")
    private String Suffix;

    @JsonProperty("Title")
    private String Title;

    @JsonProperty("MiddleName")
    private String MiddleName;

    @JsonProperty("Notes")
    private String Notes;

    @JsonProperty("FamilyName")
    private String FamilyName;

    @JsonProperty("PrimaryPhone")
    private CustomerRequest.PrimaryPhone PrimaryPhone;

    @JsonProperty("CompanyName")
    private String CompanyName;

    @JsonProperty("BillAddr")
    private CustomerRequest.BillAddr BillAddr;

    @JsonProperty("GivenName")
    private String GivenName;

    @JsonProperty("SyncToken")
    private String SyncToken;

    private String domain;

    @JsonProperty("BillWithParent")
    private boolean BillWithParent;

    private boolean sparse;

    @JsonProperty("Active")
    private boolean Active;

    @JsonProperty("Job")
    private boolean Job;

    @JsonProperty("BalanceWithJobs")
    private double BalanceWithJobs;

    @JsonProperty("PreferredDeliveryMethod")
    private String PreferredDeliveryMethod;

    @JsonProperty("Taxable")
    private boolean Taxable;

    @JsonProperty("PrintOnCheckName")
    private String PrintOnCheckName;

    @JsonProperty("Balance")
    private double Balance;

    @JsonProperty("Id")
    private String Id;

}
