package com.quickbook.playground.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountRef {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "value", required = true)
    private String value;
}
