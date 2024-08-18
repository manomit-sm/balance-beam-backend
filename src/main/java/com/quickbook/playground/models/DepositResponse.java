package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepositResponse {

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

    @JsonProperty("DepositToAccountRef")
    private DepositToAccountRef DepositToAccountRef;

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
    public static class DepositToAccountRef {
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
        @JsonProperty("LineNum")
        private Long LineNum;
        @JsonProperty("DepositLineDetail")
        private DepositLineDetail DepositLineDetail;
        @JsonProperty("LinkedTxn")
        private List<LinkedTxn> LinkedTxn;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class DepositLineDetail {
        @JsonProperty("AccountRef")
        private AccountRef AccountRef;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class LinkedTxn {
        @JsonProperty("TxnLineId")
        private String TxnLineId;
        @JsonProperty("TxnId")
        private String TxnId;
        @JsonProperty("TxnType")
        private String TxnType;
    }

}
