package com.quickbook.playground.services;

import com.intuit.oauth2.exception.InvalidRequestException;
import com.intuit.oauth2.exception.OAuthException;
import com.quickbook.playground.bo.AuthResponse;

public interface QuickBook {
    AuthResponse getAccessToken(String authCode, String redirectUrl) throws OAuthException;
    String getAccessUri(String clientId, String clientSecret, String redirectUrl) throws InvalidRequestException;

    AuthResponse getAccessToken(String refreshToken) throws OAuthException;
}
