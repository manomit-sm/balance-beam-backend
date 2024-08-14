package com.quickbook.playground.services;

import com.quickbook.playground.bo.AccountPayload;
import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.models.AccountResponse;

import java.io.IOException;
import java.util.List;

public interface AccountService {
    AccountResponse createAccount(AccountPayload accountPayload, HeaderPayload header) throws IOException;
    List<AccountResponse> retrieveAllAccount(HeaderPayload header) throws IOException;
}
