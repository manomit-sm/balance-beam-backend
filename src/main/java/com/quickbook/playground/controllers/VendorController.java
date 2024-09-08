package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.*;
import com.quickbook.playground.models.VendorResponse;
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
@RequestMapping("/vendor")
@Slf4j
@Tag(name = "Vendor", description = "Vendors of QuickBook")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class VendorController {

    private final GenericService<VendorRequest, VendorResponse> genericService;


    @Operation(
            summary = "Create a vendor",
            description = "Create a vendor in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<VendorResponse> createVendor(
            @Valid @RequestBody VendorRequest vendorRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final VendorResponse vendorResponse = genericService.create(vendorRequest, new HeaderPayload(accessToken, realmId), "vendor", "Vendor", VendorResponse.class);
        return ResponseEntity.ok(vendorResponse);
    }

    @Operation(
            summary = "Vendor list",
            description = "List all the vendors in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<List<VendorResponse>> listVendor(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {
        return ResponseEntity.ok(genericService.list(new HeaderPayload(accessToken,realmId), "vendor", "Vendor"));
    }

    @Operation(
            summary = "Retrieve a vendor",
            description = "Retrieve a vendor id in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> getVendorById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.getById(new HeaderPayload(accessToken, realmId), id, "vendor", "Vendor", VendorResponse.class));
    }

    @Operation(
            summary = "Update a vendor",
            description = "Update a vendor in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<VendorResponse> updateVendor(
            @Valid @RequestBody VendorRequest vendorUpdateRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final VendorResponse vendorResponse = genericService.update(vendorUpdateRequest, new HeaderPayload(accessToken, realmId), "vendor", "Vendor", VendorResponse.class);

        return ResponseEntity.ok(vendorResponse);
    }
}
