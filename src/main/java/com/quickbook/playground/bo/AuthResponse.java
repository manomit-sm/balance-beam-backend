package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(
        @JsonProperty("accessToken") String accessToken,
        @JsonProperty("tokenType") String tokenType,
        @JsonProperty("refreshToken") String refreshToken,
        @JsonProperty("expiresIn") Long expiresIn) {
}
