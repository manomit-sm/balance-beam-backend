package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.*;
import com.quickbook.playground.models.CreditCardPaymentResponse;
import com.quickbook.playground.models.DeletedResponse;
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
@RequestMapping("/credit-card-payment")
@Slf4j
@Tag(name = "Credit Card Payment", description = "Credit Card Payment of QuickBook")
@RequiredArgsConstructor
public class CreditCardPaymentController {

    private final GenericService<CreditCardPaymentRequest, CreditCardPaymentResponse> genericService;

    private final GenericService<CreditCardPaymentRequest, DeletedResponse> deleteServiceRequest;
    @Operation(
            summary = "Create a credit card payment",
            description = "Create a credit card payment in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<CreditCardPaymentResponse> createPayment(
            @Valid @RequestBody CreditCardPaymentRequest cardPaymentRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final CreditCardPaymentResponse creditCardPaymentResponse = genericService.create(cardPaymentRequest, new HeaderPayload(accessToken, realmId), "creditcardpayment", "CreditCardPaymentTxn", CreditCardPaymentResponse.class);
        return ResponseEntity.ok(creditCardPaymentResponse);
    }

    @Operation(
            summary = "Credit Card Payment list",
            description = "List all the credit card payment in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<List<CreditCardPaymentResponse>> listCreditCardPayment(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {
        return ResponseEntity.ok(genericService.list(new HeaderPayload(accessToken,realmId), "creditcardpayment", "CreditCardPaymentTxn"));
    }

    @Operation(
            summary = "Retrieve a credit card payment",
            description = "Retrieve a credit card payment id in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CreditCardPaymentResponse> getCreditCardPaymentById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.getById(new HeaderPayload(accessToken, realmId), id, "creditcardpayment", "CreditCardPaymentTxn", CreditCardPaymentResponse.class));
    }

    @Operation(
            summary = "Update a credit card payment",
            description = "Update a credit card payment in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<CreditCardPaymentResponse> updateCreditCardPayment(
            @Valid @RequestBody CreditCardPaymentRequest cardPaymentRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final CreditCardPaymentResponse cardPaymentResponse = genericService.update(cardPaymentRequest, new HeaderPayload(accessToken, realmId), "creditcardpayment", "CreditCardPaymentTxn", CreditCardPaymentResponse.class);
        return ResponseEntity.ok(cardPaymentResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeletedResponse> deleteCreditCardPayment(
            @Valid @RequestBody CreditCardPaymentRequest cardPaymentRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(deleteServiceRequest.delete(cardPaymentRequest, new HeaderPayload(accessToken, realmId), "creditcardpayment", "CreditCardPaymentTxn"));
    }
}
