package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeletedResponse(
        @JsonProperty("domain") String domain,
        @JsonProperty("status") String status,
        @JsonProperty("Id") String Id
) {
}
