package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.*;
import com.quickbook.playground.models.DeletedResponse;
import com.quickbook.playground.models.DepositResponse;
import com.quickbook.playground.models.PurchaseResponse;
import com.quickbook.playground.services.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/deposit")
@Slf4j
@Tag(name = "Deposit", description = "Deposit of QuickBook")
@RequiredArgsConstructor
public class DepositController {

    private final GenericService<DepositRequest, DepositResponse> genericService;

    private final GenericService<DepositUpdateRequest, DepositResponse> updateServiceRequest;

    private final GenericService<DeleteRequest, DeletedResponse> deleteServiceRequest;

    @Operation(
            summary = "Create a deposit",
            description = "Create a deposit in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<DepositResponse> createDeposit(
            @Valid @RequestBody DepositRequest depositRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final DepositResponse depositResponse = genericService.create(depositRequest, new HeaderPayload(accessToken, realmId), "deposit", "Deposit", DepositResponse.class);
        return ResponseEntity.ok(depositResponse);
    }

    @Operation(
            summary = "Deposit list",
            description = "List all the deposits in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<List<DepositResponse>> lisDeposit(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {
        return ResponseEntity.ok(genericService.list(new HeaderPayload(accessToken,realmId), "deposit", "Deposit"));
    }

    @Operation(
            summary = "Retrieve a deposit",
            description = "Retrieve a deposit id in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepositResponse> getDepositById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.getById(new HeaderPayload(accessToken, realmId), id, "deposit", "Deposit", DepositResponse.class));
    }

    @Operation(
            summary = "Update a deposit",
            description = "Update a deposit in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<DepositResponse> updatePurchase(
            @Valid @RequestBody DepositUpdateRequest depositUpdateRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final DepositResponse depositResponse = updateServiceRequest.update(depositUpdateRequest, new HeaderPayload(accessToken, realmId), "deposit", "Deposit", DepositResponse.class);
        return ResponseEntity.ok(depositResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeletedResponse> deletePurchase(
            @Valid @RequestBody DeleteRequest deleteRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(deleteServiceRequest.delete(deleteRequest, new HeaderPayload(accessToken, realmId), "purchase", "Purchase"));
    }
}
