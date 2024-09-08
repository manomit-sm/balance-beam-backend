package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.DeleteRequest;
import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.bo.InvoiceRequest;
import com.quickbook.playground.bo.PaymentRequest;
import com.quickbook.playground.models.DeletedResponse;
import com.quickbook.playground.models.PaymentResponse;
import com.quickbook.playground.services.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/payment")
@Slf4j
@Tag(name = "Payment", description = "Payments of QuickBook")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PaymentController {

    private final GenericService<PaymentRequest, PaymentResponse> paymentService;
    private final GenericService<DeleteRequest, DeletedResponse> paymentDeleteService;

    @Operation(
            summary = "Create a payment",
            description = "Create a payment in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest paymentRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final PaymentResponse paymentResponse = paymentService.create(paymentRequest, new HeaderPayload(accessToken, realmId), "payment", "Payment", PaymentResponse.class);
        return ResponseEntity.ok(paymentResponse);
    }

    @Operation(
            summary = "Payment list",
            description = "List all the payments in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<Object> listPayment(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {
        return ResponseEntity.ok(paymentService.list(new HeaderPayload(accessToken, realmId), "payment", "Payment"));
    }

    @Operation(
            summary = "Retrieve a payment",
            description = "Retrieve a payment in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(paymentService.getById(new HeaderPayload(accessToken, realmId), id, "payment", "Payment", PaymentResponse.class));
    }
    @Operation(
            summary = "Generate PDF",
            description = "Generate PDF for a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "/pdf/{id}", produces = {MediaType.APPLICATION_PDF_VALUE})
    public Mono<byte[]> generatePdf(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {

        return paymentService.generatePdfFromId(new HeaderPayload(accessToken, realmId), id, "payment");
    }

    @Operation(
            summary = "Send Email",
            description = "Send email for payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/email/{id}")
    public ResponseEntity<Object> sendEmail(
            @PathVariable String id,
            @RequestParam("email") String toEmail,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) {
        return ResponseEntity.ok(paymentService.sendEmailFromId(new HeaderPayload(accessToken, realmId), id, "payment", toEmail));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeletedResponse> deletePayment(
            @Valid @RequestBody DeleteRequest deleteRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(paymentDeleteService.delete(deleteRequest, new HeaderPayload(accessToken, realmId), "payment", "Payment"));
    }

    @Operation(
            summary = "Update a payment",
            description = "Update a payment in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<Object> updatePayment(
            @Valid @RequestBody PaymentRequest paymentRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final PaymentResponse updated = paymentService.update(paymentRequest, new HeaderPayload(accessToken, realmId), "payment", "Payment", PaymentResponse.class);
        return ResponseEntity.ok(updated);
    }
}
