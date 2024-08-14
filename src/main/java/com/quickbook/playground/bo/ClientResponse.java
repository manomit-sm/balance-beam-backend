package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientResponse(
        @JsonProperty("email") String email,
        @JsonProperty("redirectUrl") String redirectUrl
) {
}
