package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PaymentResponseLine(
        @JsonProperty("Amount") Double Amount,
        @JsonProperty("LinkedTxn")List<PaymentResponseLineLinkedTxn> LinkedTxn
        ) {
}
