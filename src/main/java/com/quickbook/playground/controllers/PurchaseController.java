package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.bo.DeleteRequest;
import com.quickbook.playground.bo.PurchaseRequest;
import com.quickbook.playground.bo.PurchaseUpdateRequest;
import com.quickbook.playground.models.DeletedResponse;
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
@RequestMapping("/purchase")
@Slf4j
@Tag(name = "Purchase", description = "Purchase of QuickBook")
@RequiredArgsConstructor
public class PurchaseController {

    private final GenericService<PurchaseRequest, PurchaseResponse> genericService;

    private final GenericService<PurchaseUpdateRequest, PurchaseResponse> updateServiceRequest;

    private final GenericService<DeleteRequest, DeletedResponse> deleteServiceRequest;

    @Operation(
            summary = "Create a purchase",
            description = "Create a purchase in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<PurchaseResponse> createPurchase(
            @Valid @RequestBody PurchaseRequest purchaseRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final PurchaseResponse purchaseResponse = genericService.create(purchaseRequest, new HeaderPayload(accessToken, realmId), "purchase", "Purchase", PurchaseResponse.class);
        return ResponseEntity.ok(purchaseResponse);
    }

    @Operation(
            summary = "Purchase list",
            description = "List all the purchases in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<List<PurchaseResponse>> listPurchase(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {
        return ResponseEntity.ok(genericService.list(new HeaderPayload(accessToken,realmId), "purchase", "Purchase"));
    }

    @Operation(
            summary = "Retrieve a purchase",
            description = "Retrieve a purchase id in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponse> getPurchaseById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.getById(new HeaderPayload(accessToken, realmId), id, "purchase", "Purchase", PurchaseResponse.class));
    }

    @Operation(
            summary = "Update a purchase",
            description = "Update a purchase in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<PurchaseResponse> updatePurchase(
            @Valid @RequestBody PurchaseUpdateRequest purchaseUpdateRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final PurchaseResponse purchase = updateServiceRequest.update(purchaseUpdateRequest, new HeaderPayload(accessToken, realmId), "purchase", "Purchase", PurchaseResponse.class);
        return ResponseEntity.ok(purchase);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeletedResponse> deletePurchase(
            @Valid @RequestBody DeleteRequest purchaseUpdateRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(deleteServiceRequest.delete(purchaseUpdateRequest, new HeaderPayload(accessToken, realmId), "purchase", "Purchase"));
    }
}
