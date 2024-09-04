package com.quickbook.playground.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intuit.ipp.data.ColData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionListIntermediaryResponse {
    @JsonProperty("ColData")
    private List<ColData> ColData;
}
