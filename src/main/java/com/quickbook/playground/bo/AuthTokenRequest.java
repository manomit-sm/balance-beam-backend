package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record AuthTokenRequest(
        @NotNull @JsonProperty("email") String email,
        @NotNull @JsonProperty("authCode") String authCode,
        @NotNull @JsonProperty("realmId") Long realmId
) {

}
