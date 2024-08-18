package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseResponse {

    @JsonProperty("SyncToken")
    private String SyncToken;

    private String domain;

    @JsonProperty("TxnDate")
    private String TxnDate;

    @JsonProperty("TotalAmt")
    private Double TotalAmt;

    @JsonProperty("PaymentType")
    private String Cash;

    private boolean sparse;

    @JsonProperty("Line")
    private List<Line> Line;

    @JsonProperty("AccountRef")
    private AccountRef AccountRef;

    @JsonProperty("Id")
    private String Id;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class AccountRef {
        private String name;
        private String value;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Line {
        @JsonProperty("DetailType")
        private String DetailType;
        @JsonProperty("Amount")
        private Double Amount;
        @JsonProperty("Id")
        private String Id;
        @JsonProperty("AccountBasedExpenseLineDetail")
        private AccountBasedExpenseLineDetail AccountBasedExpenseLineDetail;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class AccountBasedExpenseLineDetail {
        @JsonProperty("BillableStatus")
        private String BillableStatus;
        @JsonProperty("TaxCodeRef")
        private TaxCodeRef TaxCodeRef;
        @JsonProperty("AccountRef")
        private AccountRef AccountRef;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class TaxCodeRef {
        private String value;
    }
}
