package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.*;
import com.quickbook.playground.models.CustomerResponse;
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
@RequestMapping("/customer")
@Slf4j
@Tag(name = "Customer", description = "Customers of QuickBook")
@RequiredArgsConstructor
public class CustomerController {

    private final GenericService<CustomerRequest, CustomerResponse> genericService;


    @Operation(
            summary = "Create a customer",
            description = "Create a customer in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest customerRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final CustomerResponse customerResponse = genericService.create(customerRequest, new HeaderPayload(accessToken, realmId), "customer", "Customer", CustomerResponse.class);
        return ResponseEntity.ok(customerResponse);
    }

    @Operation(
            summary = "Customer list",
            description = "List all the customers in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<List<CustomerResponse>> listCustomer(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) throws IOException {
        return ResponseEntity.ok(genericService.list(new HeaderPayload(accessToken,realmId), "customer", "Customer"));
    }

    @Operation(
            summary = "Retrieve a customer",
            description = "Retrieve a customer id in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(genericService.getById(new HeaderPayload(accessToken, realmId), id, "customer", "Customer", CustomerResponse.class));
    }

    @Operation(
            summary = "Update a customer",
            description = "Update a customer in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @Valid @RequestBody CustomerRequest customerRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final CustomerResponse customerResponse = genericService.update(customerRequest, new HeaderPayload(accessToken, realmId), "customer", "Customer", CustomerResponse.class);

        return ResponseEntity.ok(customerResponse);
    }
}
