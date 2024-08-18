package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositUpdateRequest implements Serializable {
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("SyncToken")
    private String SyncToken;
    private String domain;

    @JsonProperty("DepositToAccountRef")
    @NotNull
    private DepositToAccountRef DepositToAccountRef;
    @JsonProperty("Line")
    @NotNull
    private List<DepositUpdateLine> Line;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepositToAccountRef {
        @NotNull
        @NotEmpty
        private String value;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepositUpdateLine {
        @JsonProperty("DetailType")
        @NotNull
        @NotEmpty
        private String DetailType;
        @JsonProperty("Amount")
        @NotNull
        @NotEmpty
        private Double Amount;
        @JsonProperty("Id")
        @NotNull
        @NotEmpty
        private String Id;
        //@JsonProperty("LinkedTxn")
        //private List<LinkedTxn> LinkedTxn;
        @JsonProperty("DepositLineDetail")
        @NotNull
        private DepositUpdateLineDetail DepositLineDetail;
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepositUpdateLineDetail {
        @JsonProperty("AccountRef")
        @NotNull
        private DepositRequest.AccountRef AccountRef;
    }
}
