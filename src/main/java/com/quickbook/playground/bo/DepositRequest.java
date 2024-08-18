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
public class DepositRequest implements Serializable {

    @JsonProperty("DepositToAccountRef")
    @NotNull
    private DepositToAccountRef DepositToAccountRef;
    @JsonProperty("Line")
    @NotNull
    private List<DepositLine> Line;

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
    public static class DepositLine {
        @JsonProperty("DetailType")
        @NotNull
        @NotEmpty
        private String DetailType;
        @JsonProperty("Amount")
        @NotNull
        @NotEmpty
        private Double Amount;
        @JsonProperty("DepositLineDetail")
        @NotNull
        private DepositLineDetail DepositLineDetail;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DepositLineDetail {
        @JsonProperty("AccountRef")
        @NotNull
        private AccountRef AccountRef;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountRef {
        private String name;
        @NotNull
        @NotEmpty
        private String value;
    }
}
