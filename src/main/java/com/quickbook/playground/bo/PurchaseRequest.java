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
public class PurchaseRequest implements Serializable {
    @JsonProperty("PaymentType")
    @NotNull
    private String PaymentType;
    @JsonProperty("AccountRef")
    @NotNull
    private AccountRef AccountRef;
    @JsonProperty("Line")
    @NotNull
    private List<Line> Line;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountRef {
        @NotNull
        @NotEmpty
        private String value;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Line {
        @JsonProperty("DetailType")
        @NotNull
        @NotEmpty
        private String DetailType;
        @JsonProperty("Amount")
        @NotNull
        @NotEmpty
        private Double Amount;
        @JsonProperty("AccountBasedExpenseLineDetail")
        @NotNull
        private AccountBasedExpenseLineDetail AccountBasedExpenseLineDetail;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountBasedExpenseLineDetail {
        @JsonProperty("AccountRef")
        @NotNull
        private AccountRef AccountRef;
    }
}
