package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quickbook.playground.bo.VendorRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorResponse {

    private String domain;
    @JsonProperty("Vendor1099")
    private boolean Vendor1099;
    @JsonProperty("SyncToken")
    private String SyncToken;
    @JsonProperty("Balance")
    private double Balance;
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("BillAddr")
    private BillAddr BillAddr;

    @JsonProperty("PrimaryEmailAddr")
    @NotNull
    private PrimaryEmailAddr PrimaryEmailAddr;

    @JsonProperty("WebAddr")
    @NotNull
    private WebAddr WebAddr;

    @JsonProperty("PrimaryPhone")
    @NotNull
    private PrimaryPhone PrimaryPhone;

    @JsonProperty("DisplayName")
    @NotNull
    @NotEmpty
    private String DisplayName;

    @JsonProperty("Suffix")
    @NotNull
    @NotEmpty
    private String Suffix;

    @JsonProperty("Title")
    @NotNull
    @NotEmpty
    private String Title;

    @JsonProperty("Mobile")
    @NotNull
    private PrimaryPhone Mobile;

    @JsonProperty("FamilyName")
    @NotNull
    @NotEmpty
    private String FamilyName;

    @JsonProperty("TaxIdentifier")
    @NotNull
    @NotEmpty
    private String TaxIdentifier;

    @JsonProperty("AcctNum")
    @NotNull
    @NotEmpty
    private String AcctNum;

    @JsonProperty("CompanyName")
    @NotNull
    @NotEmpty
    private String CompanyName;


    @JsonProperty("GivenName")
    @NotNull
    @NotEmpty
    private String GivenName;

    @JsonProperty("PrintOnCheckName")
    @NotNull
    @NotEmpty
    private String PrintOnCheckName;




    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PrimaryEmailAddr {
        @JsonProperty("Address")
        private String Address;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WebAddr {
        @JsonProperty("URI")
        private String URI;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PrimaryPhone {
        @JsonProperty("FreeFormNumber")
        private String FreeFormNumber;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BillAddr {

        @JsonProperty("CountrySubDivisionCode")
        private String CountrySubDivisionCode;

        @JsonProperty("City")
        private String City;

        @JsonProperty("PostalCode")
        private String PostalCode;

        @JsonProperty("Line1")
        private String Line1;

        @JsonProperty("Line2")
        private String Line2;

        @JsonProperty("Line3")
        private String Line3;

        @JsonProperty("Country")
        private String Country;

        @JsonProperty("Lat")
        private String Lat;

        @JsonProperty("Long")
        private String Long;

        @JsonProperty("Id")
        private String Id;
    }
}
