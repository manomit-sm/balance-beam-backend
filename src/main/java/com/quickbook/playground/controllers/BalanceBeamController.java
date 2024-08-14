package com.quickbook.playground.controllers;

import com.intuit.oauth2.exception.InvalidRequestException;
import com.intuit.oauth2.exception.OAuthException;
import com.quickbook.playground.bo.AuthResponse;
import com.quickbook.playground.bo.AuthTokenRequest;
import com.quickbook.playground.bo.ClientResponse;
import com.quickbook.playground.services.BalanceBeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/balance-beam")
@RequiredArgsConstructor
@Slf4j
public class BalanceBeamController {
    private final BalanceBeamService balanceBeamService;

    @Operation(
            summary = "Get OAuth2 Url",
            description = "Get OAuth2 Url")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/oauth-url")
    public ResponseEntity<ClientResponse> getOAuthUrl(@RequestParam String email) throws InvalidRequestException, IOException {
        return ResponseEntity.ok(balanceBeamService.getClientDetails(email));
    }


    @Operation(
            summary = "Get Access Token",
            description = "Get Access Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/auth-token")
    public ResponseEntity<AuthResponse> getAuthToken(@Valid @RequestBody AuthTokenRequest authTokenRequest) throws OAuthException, IOException {
        return ResponseEntity.ok(balanceBeamService.getAuthDetails(authTokenRequest));
    }

    @Operation(
            summary = "Get Access Token from Refresh Token",
            description = "Get Access Token from Refresh Token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/refresh-token")
    public ResponseEntity<AuthResponse> getAccessTokenFromRefreshToken(
            @RequestParam String refreshToken
    ) throws OAuthException {
        return ResponseEntity.ok(balanceBeamService.getAuthDetails(refreshToken));
    }

}
