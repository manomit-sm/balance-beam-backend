package com.quickbook.playground.bo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountPayload {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String acctNum;

    @NotNull
    private String accountType;

    @NotNull
    private String accountSubType;
}

