package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.services.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/report")
@Tag(name = "Reports", description = "Reports such as Profit And Loss, Cash Flow and Balance Sheet")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ReportsController {

    private final GenericService<Object, Object> genericService;

    @Operation(
            summary = "Balance Sheet",
            description = "Balance Sheet report of the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/balance-sheet")
    public ResponseEntity<Object> getBalanceSheet(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) throws IOException {
        return ResponseEntity.ok(genericService.getBalanceSheet(new HeaderPayload(accessToken,realmId), startDate, endDate));
    }

    @Operation(
            summary = "Profit And Loss",
            description = "Profit And Loss report of the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/profit-and-loss")
    public ResponseEntity<Object> geProfitAndLoss(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) throws IOException {
        return ResponseEntity.ok(genericService.getProfitAndLoss(new HeaderPayload(accessToken,realmId), startDate, endDate));
    }

    @Operation(
            summary = "Cash Flow",
            description = "Cash Flow report of the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/cash-flow")
    public ResponseEntity<Object> getCashFlow(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) throws IOException {
        return ResponseEntity.ok(genericService.getCashFlow(new HeaderPayload(accessToken,realmId), startDate, endDate));
    }
}
