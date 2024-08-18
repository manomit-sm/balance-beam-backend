package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardPaymentRequest implements Serializable {
    @JsonProperty("PrivateNote")
    private String PrivateNote;
    @JsonProperty(value = "TxnDate", required = true)
    private String TxnDate;
    @JsonProperty("Amount")
    private Double Amount;
    @JsonProperty(value = "BankAccountRef", required = true)
    private BankAccountRef BankAccountRef;
    @JsonProperty(value = "CreditCardAccountRef", required = true)
    private CreditCardAccountRef CreditCardAccountRef;
    @JsonProperty("SyncToken")
    private String SyncToken;
    @JsonProperty("Id")
    private String Id;

}
