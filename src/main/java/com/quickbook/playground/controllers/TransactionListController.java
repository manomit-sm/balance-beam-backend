package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.models.TransactionListResponse;
import com.quickbook.playground.models.VendorResponse;
import com.quickbook.playground.services.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@Slf4j
@Tag(name = "Transaction List", description = "Transaction List of QuickBook")
@RequiredArgsConstructor
public class TransactionListController {
    private final GenericService<Object, Object> genericService;

    @Operation(
            summary = "Transaction List",
            description = "List all the transactions in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<List<TransactionListResponse>> listTransaction(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) throws IOException {
        return ResponseEntity.ok(genericService.getTransactionList(new HeaderPayload(accessToken,realmId), startDate, endDate));
    }
}
