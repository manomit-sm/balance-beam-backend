package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.DeleteRequest;
import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.bo.InvoiceRequest;
import com.quickbook.playground.models.DeletedResponse;
import com.quickbook.playground.services.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/invoice")
@Slf4j
@Tag(name = "Invoice", description = "Invoices of QuickBook")
@RequiredArgsConstructor
public class InvoiceController {

    private final GenericService<InvoiceRequest, Object> invoiceService;
    private final GenericService<DeleteRequest, DeletedResponse> invoiceDeleteService;

    @Operation(
            summary = "Create an invoice",
            description = "Create an invoice in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<Object> createInvoice(
            @Valid @RequestBody InvoiceRequest invoiceRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final Object invoiceResponse = invoiceService.create(invoiceRequest, new HeaderPayload(accessToken, realmId), "invoice", "Invoice", Object.class);
        return ResponseEntity.ok(invoiceResponse);
    }

    @Operation(
            summary = "Invoice list",
            description = "List all the invoices in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<Object> listInvoices(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {
        return ResponseEntity.ok(invoiceService.list(new HeaderPayload(accessToken, realmId), "invoice", "Invoice"));
    }

    @Operation(
            summary = "Retrieve an invoice",
            description = "Retrieve an invoice in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoiceById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(invoiceService.getById(new HeaderPayload(accessToken, realmId), id, "invoice", "Invoice", Object.class));
    }
    @Operation(
            summary = "Generate PDF",
            description = "Generate PDF for an invoice id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "/pdf/{id}", produces = {MediaType.APPLICATION_PDF_VALUE})
    public Mono<byte[]> generatePdf(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {

       return invoiceService.generatePdfFromId(new HeaderPayload(accessToken, realmId), id, "invoice");
    }

    @Operation(
            summary = "Send Email",
            description = "Send email for invoice")
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
        return ResponseEntity.ok(invoiceService.sendEmailFromId(new HeaderPayload(accessToken, realmId), id, "invoice", toEmail));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeletedResponse> deleteInvoice(
            @Valid @RequestBody DeleteRequest deleteRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(invoiceDeleteService.delete(deleteRequest, new HeaderPayload(accessToken, realmId), "invoice", "Invoice"));
    }

    @Operation(
            summary = "Update an invoice",
            description = "Update an invoice in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<Object> updateInvoice(
            @Valid @RequestBody InvoiceRequest invoiceRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final Object updated = invoiceService.update(invoiceRequest, new HeaderPayload(accessToken, realmId), "invoice", "Invoice", Object.class);
        return ResponseEntity.ok(updated);
    }
}
