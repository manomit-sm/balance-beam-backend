package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record DeleteRequest(
        @NotNull @JsonProperty("SyncToken") String SyncToken,
        @NotNull @JsonProperty("Id") String Id
) {
}
