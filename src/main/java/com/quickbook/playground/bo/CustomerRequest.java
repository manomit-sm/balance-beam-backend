package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotNull
    @NotEmpty
    @JsonProperty("FullyQualifiedName")
    private String FullyQualifiedName;

    @NotNull
    @JsonProperty("PrimaryEmailAddr")
    private PrimaryEmailAddr PrimaryEmailAddr;

    @NotNull
    @NotEmpty
    @JsonProperty("DisplayName")
    private String DisplayName;

    @NotNull
    @NotEmpty
    @JsonProperty("Suffix")
    private String Suffix;

    @NotNull
    @NotEmpty
    @JsonProperty("Title")
    private String Title;

    @JsonProperty("MiddleName")
    private String MiddleName;

    @JsonProperty("Notes")
    private String Notes;

    @NotNull
    @NotEmpty
    @JsonProperty("FamilyName")
    private String FamilyName;

    @JsonProperty("PrimaryPhone")
    private PrimaryPhone PrimaryPhone;

    @NotNull
    @NotEmpty
    @JsonProperty("CompanyName")
    private String CompanyName;

    @NotNull
    @JsonProperty("BillAddr")
    private BillAddr BillAddr;

    @NotNull
    @NotEmpty
    @JsonProperty("GivenName")
    private String GivenName;



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

        @JsonProperty("Country")
        private String Country;
    }

    @JsonProperty("Id")
    private String Id;
    @JsonProperty("SyncToken")
    private String SyncToken;
}
