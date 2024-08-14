package com.quickbook.playground.services;

import com.intuit.oauth2.exception.InvalidRequestException;
import com.intuit.oauth2.exception.OAuthException;
import com.quickbook.playground.bo.AuthResponse;
import com.quickbook.playground.bo.AuthTokenRequest;
import com.quickbook.playground.bo.ClientResponse;

import java.io.IOException;

public interface BalanceBeamService {
    ClientResponse getClientDetails(String email) throws IOException, InvalidRequestException;
    AuthResponse getAuthDetails(AuthTokenRequest authTokenRequest) throws IOException, OAuthException;

    AuthResponse getAuthDetails(String refreshToken) throws OAuthException;
}
