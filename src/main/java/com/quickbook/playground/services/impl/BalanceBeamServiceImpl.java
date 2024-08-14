package com.quickbook.playground.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.oauth2.exception.InvalidRequestException;
import com.intuit.oauth2.exception.OAuthException;
import com.quickbook.playground.bo.AuthResponse;
import com.quickbook.playground.bo.AuthTokenRequest;
import com.quickbook.playground.bo.ClientResponse;
import com.quickbook.playground.services.BalanceBeamService;
import com.quickbook.playground.services.QuickBook;
import com.quickbook.playground.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceBeamServiceImpl implements BalanceBeamService {

    private final ObjectMapper objectMapper;

    private final QuickBook quickBook;


    @Override
    public ClientResponse getClientDetails(String email) throws IOException, InvalidRequestException {
        var client = AppUtils.clientDetails(email, objectMapper);
        final String accessUri = quickBook.getAccessUri(client.getClientId(), client.getClientSecret(), client.getRedirectUrl());
        return new ClientResponse(email, accessUri);
    }

    @Override
    public AuthResponse getAuthDetails(AuthTokenRequest authTokenRequest) throws IOException, OAuthException {
        var client = AppUtils.clientDetails(authTokenRequest.email(), objectMapper);
        return quickBook.getAccessToken(authTokenRequest.authCode(), client.getRedirectUrl());
    }

    @Override
    public AuthResponse getAuthDetails(String refreshToken) throws OAuthException {
        return quickBook.getAccessToken(refreshToken);
    }
}
