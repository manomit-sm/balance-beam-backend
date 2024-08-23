package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PaymentResponse(
        @JsonProperty("Id") String Id,
        @JsonProperty("SyncToken") String SyncToken,
        @JsonProperty("domain") String domain,
        @JsonProperty("DepositToAccountRef") Object DepositToAccountRef,
        @JsonProperty("UnappliedAmt") Double UnappliedAmt,
        @JsonProperty("TxnDate") String TxnDate,
        @JsonProperty("TotalAmt") Double TotalAmt,
        @JsonProperty("ProcessPayment") boolean ProcessPayment,
        @JsonProperty("sparse") boolean sparse,
        @JsonProperty("Line") List<PaymentResponseLine> Line,
        @JsonProperty("CustomerRef") Object CustomerRef
) {
}
