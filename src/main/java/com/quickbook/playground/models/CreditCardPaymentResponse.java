package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quickbook.playground.bo.BankAccountRef;
import com.quickbook.playground.bo.CreditCardAccountRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCardPaymentResponse {
    @JsonProperty("SyncToken")
    private String SyncToken;
    private String domain;
    @JsonProperty("CreditCardAccountRef")
    private CreditCardAccountRef CreditCardAccountRef;
    @JsonProperty("TxnDate")
    private String TxnDate;
    @JsonProperty("CurrencyRef")
    private CurrencyRef CurrencyRef;
    @JsonProperty("Amount")
    private Double Amount;
    private boolean sparse;
    @JsonProperty("BankAccountRef")
    private BankAccountRef BankAccountRef;
    @JsonProperty("Id")
    private String Id;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CurrencyRef {
        private String name;
        private String value;
    }
}
