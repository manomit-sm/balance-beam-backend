package com.quickbook.playground.services.impl;

import com.intuit.oauth2.client.OAuth2PlatformClient;
import com.intuit.oauth2.config.Environment;
import com.intuit.oauth2.config.OAuth2Config;
import com.intuit.oauth2.data.BearerTokenResponse;
import com.intuit.oauth2.exception.OAuthException;
import com.quickbook.playground.services.QuickBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@Service
@Slf4j
public class QuickBookImpl implements QuickBook {

    private final String refreshToken;
    private final String clientId;

    private final String clientSecret;

    private String token;
    public QuickBookImpl(
            @Value("${spring.quick-book.refresh-token}") String refreshToken,
            @Value("${spring.quick-book.client-id}") String clientId,
            @Value("${spring.quick-book.client-secret}") String clientSecret
    ) {
        this.refreshToken = refreshToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.token = null;
    }
    @Override
    public String getAccessToken() {
        try {
            // log.info("Client Id {} : Client Secret {} : RefreshToken {} ", clientId, clientSecret, refreshToken);
            final String accessToken = System.getProperty("quick-book.access-token");
            final String expireTime = System.getProperty("quick-book.access-token.expire");
            if (Objects.nonNull(expireTime) && Objects.nonNull(accessToken)) {
                final Duration duration = Duration.between(Instant.parse(expireTime), Instant.now());
                // log.info("Token Expire Duration {} - {}", duration.getSeconds(), Long.parseLong(System.getProperty("quick-book.access-token.expire-in-seconds")));
                if (duration.getSeconds() > Long.parseLong(System.getProperty("quick-book.access-token.expire-in-seconds"))) {
                    token = tokenResponse();
                } else {
                    token = accessToken;
                }
            } else {
                token = tokenResponse();
            }

        } catch (OAuthException exception) {
            log.error("Exception {}", exception.getMessage(), exception);
        }
        return token;
    }

    private BearerTokenResponse retrieveToken() throws OAuthException {
        OAuth2Config oAuth2Config = new OAuth2Config.OAuth2ConfigBuilder(clientId, clientSecret)
                .callDiscoveryAPI(Environment.SANDBOX).buildConfig();

        OAuth2PlatformClient client  = new OAuth2PlatformClient(oAuth2Config);
        return client.refreshToken(refreshToken);
    }

    private String tokenResponse() throws OAuthException {
        var bearerToken = retrieveToken();
        System.setProperty("quick-book.access-token", bearerToken.getAccessToken());
        System.setProperty("quick-book.access-token.expire", String.valueOf(Instant.now()));
        System.setProperty("quick-book.access-token.expire-in-seconds", String.valueOf(bearerToken.getExpiresIn()));
        return bearerToken.getAccessToken();
    }
}
