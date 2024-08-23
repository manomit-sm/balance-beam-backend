package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    @JsonProperty("Id")
    @JsonPropertyDescription("Only For Update")
    private String Id;

    @JsonProperty("SyncToken")
    @JsonPropertyDescription("Only For Update")
    private String SyncToken;


    @JsonProperty("PaymentRefNum")
    @JsonPropertyDescription("A Check # for a check, envelope # for a cash donation")
    private String PaymentRefNum;

    @JsonProperty("Line")
    private List<PaymentRequestLine> Line;

    @JsonProperty("TotalAmt")
    private Double TotalAmt;

    @JsonProperty("CustomerRef")
    private PaymentCustomerRef CustomerRef;

    @JsonProperty("DepositToAccountRef")
    private PaymentDepositToAccountRef DepositToAccountRef;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentRequestLine {
        @JsonProperty("Amount")
        private Double Amount;

        @JsonProperty("LinkedTxn")
        private List<PaymentRequestLineLinkedTxn> LinkedTxn;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentRequestLineLinkedTxn {
        @JsonProperty("TxnId")
        private String TxnId;

        @JsonProperty("TxnType")
        private String TxnType;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentCustomerRef {
        private String value;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentDepositToAccountRef {
        private String value;
    }
}
