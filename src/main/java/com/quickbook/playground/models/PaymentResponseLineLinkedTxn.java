package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentResponseLineLinkedTxn(
        @JsonProperty("TxnId") String TxnId,
        @JsonProperty("TxnType") String TxnType
) {
}
