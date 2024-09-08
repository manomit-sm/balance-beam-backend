package com.quickbook.playground.services.impl;

import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.config.Scope;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.InvalidRequestException;
import com.intuit.oauth2.exception.OAuthException;
import com.quickbook.playground.models.AuthResponse;
import com.quickbook.playground.services.QuickBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class QuickBookImpl implements QuickBook {
    @Override
    public AuthResponse getAccessToken(String authCode, String redirectUrl, String clientId, String clientSecret) throws OAuthException {
        OAuth2Config oAuth2Config = getOAuth2ConfigObject(clientId, clientSecret);
        OAuth2PlatformClient client  = new OAuth2PlatformClient(oAuth2Config);
        final BearerTokenResponse bearerTokenResponse = client.retrieveBearerTokens(authCode, redirectUrl);
        return authResponse(bearerTokenResponse);
    }

    @Override
    public String getAccessUri(String clientId, String clientSecret, String redirectUrl) throws InvalidRequestException {
        OAuth2Config oAuth2Config = getOAuth2ConfigObject(clientId, clientSecret);
        var csrfToken = oAuth2Config.generateCSRFToken();
        List<Scope> scopes = new ArrayList<>();
        scopes.add(Scope.All);
        return oAuth2Config.prepareUrl(scopes, redirectUrl, csrfToken);
    }

    @Override
    public AuthResponse getAccessToken(String refreshToken, String clientId, String clientSecret) throws OAuthException {
        OAuth2Config oAuth2Config = getOAuth2ConfigObject(clientId, clientSecret);
        OAuth2PlatformClient client  = new OAuth2PlatformClient(oAuth2Config);
        final BearerTokenResponse bearerTokenResponse = client.refreshToken(refreshToken);
        return authResponse(bearerTokenResponse);
    }

    private OAuth2Config getOAuth2ConfigObject(final String clientId, final String clientSecret) {
        return new OAuth2Config.OAuth2ConfigBuilder(clientId, clientSecret)
                .callDiscoveryAPI(Environment.SANDBOX).buildConfig();
    }

    private AuthResponse authResponse(BearerTokenResponse bearerTokenResponse) {
        return new AuthResponse(
            bearerTokenResponse.getAccessToken(),
            bearerTokenResponse.getTokenType(),
            bearerTokenResponse.getRefreshToken(),
            bearerTokenResponse.getExpiresIn(),
            bearerTokenResponse.getIdToken()
        );
    }
}
