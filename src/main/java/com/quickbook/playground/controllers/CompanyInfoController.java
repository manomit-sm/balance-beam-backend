package com.quickbook.playground.controllers;


import com.quickbook.playground.bo.CompanyInfoPayload;
import com.quickbook.playground.bo.HeaderPayload;
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

@RestController
@RequestMapping("/company-info")
@Slf4j
@Tag(name = "Company Info", description = "Company Information")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CompanyInfoController {

    private final GenericService<CompanyInfoPayload, Object> genericService;

    @Operation(
            summary = "Fetch company info",
            description = "Fetch company info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<Object> companyInfo(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.list(new HeaderPayload(accessToken, realmId), "CompanyInfo", "CompanyInfo"));
    }

    @Operation(
            summary = "Update company info",
            description = "Update company info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<Object> updateCompanyInfo(
            @Valid @RequestBody CompanyInfoPayload companyInfoPayload,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {

        return ResponseEntity.ok(genericService.update(companyInfoPayload, new HeaderPayload(accessToken, realmId), "companyinfo", "CompanyInfo", Object.class));
    }
}
