package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.AccountPayload;
import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.models.AccountResponse;
import com.quickbook.playground.services.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/account")
@Slf4j
@Tag(name = "Account", description = "Account Management of QuickBook")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class AccountController {

    private final GenericService<AccountPayload, AccountResponse> genericService;

    @Operation(
            summary = "Create an account",
            description = "Create an account in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<AccountResponse> createAccount(
            @Valid @RequestBody AccountPayload accountPayload,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.create(accountPayload, new HeaderPayload(accessToken, realmId), "account", "Account", AccountResponse.class));
    }

    @Operation(
            summary = "Fetch all accounts",
            description = "fetches all account details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<List<AccountResponse>> listAccount(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.list(new HeaderPayload(accessToken, realmId), "account", "Account"));
    }

    @Operation(
            summary = "Fetch account by id",
            description = "Fetch account by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.getById(new HeaderPayload(accessToken, realmId), id,  "account", "Account", AccountResponse.class));
    }

}
