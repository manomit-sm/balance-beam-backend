package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompanyInfoPayload(
        @JsonProperty("SyncToken") String SyncToken,
        @JsonProperty("Id") String Id,
        @JsonProperty("LegalName") String LegalName,
        @JsonProperty("CompanyStartDate") String CompanyStartDate,
        @JsonProperty("domain") String domain,
        @JsonProperty("LegalAddr") CompanyLegalAddr LegalAddr,
        @JsonProperty("SupportedLanguages") String SupportedLanguages,
        @JsonProperty("CompanyName") String CompanyName,
        @JsonProperty("Country") String Country,
        @JsonProperty("CompanyAddr") CompanyLegalAddr CompanyAddr,

        @JsonProperty("Email") Email Email,
        @JsonProperty("PrimaryPhone") PrimaryPhone PrimaryPhone

) {

    public record CompanyLegalAddr(
            @JsonProperty("City") String City,
            @JsonProperty("Country") String Country,
            @JsonProperty("Line1") String Line1,
            @JsonProperty("PostalCode") String PostalCode,
            @JsonProperty("CountrySubDivisionCode") String CountrySubDivisionCode,
            @JsonProperty("Id") String Id
    ) {}

    public record Email(@JsonProperty("Address") String Address) {}
    public record PrimaryPhone(@JsonProperty("FreeFormNumber") String FreeFormNumber) {}
}
