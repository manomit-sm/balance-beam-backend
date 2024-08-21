package com.quickbook.playground.controllers;

import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.bo.ItemRequest;
import com.quickbook.playground.models.ItemResponse;
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

@RestController
@RequestMapping("/item")
@Slf4j
@Tag(name = "Item", description = "Items of QuickBook")
@RequiredArgsConstructor
public class ItemController {

    private final GenericService<ItemRequest, ItemResponse> itemService;

    @Operation(
            summary = "Create an item",
            description = "Create an item in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PostMapping("/create")
    public ResponseEntity<ItemResponse> createItem(
            @Valid @RequestBody ItemRequest itemRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final ItemResponse itemResponse = itemService.create(itemRequest, new HeaderPayload(accessToken, realmId), "item", "Item", ItemResponse.class);
        return ResponseEntity.ok(itemResponse);
    }

    @Operation(
            summary = "Item list",
            description = "List all the items in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/list")
    public ResponseEntity<Object> listItems(
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId) {

        try {
            return ResponseEntity.ok(itemService.list(new HeaderPayload(accessToken, realmId), "item", "Item"));
        } catch (IOException e) {
            log.error("Exception {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }

    @Operation(
            summary = "Retrieve an item",
            description = "Retrieve an item id in QuickBook for the company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(
            @PathVariable String id,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        return ResponseEntity.ok(itemService.getById(new HeaderPayload(accessToken, realmId), id, "item", "Item", ItemResponse.class));
    }

    @Operation(
            summary = "Update an item",
            description = "Update an item in QuickBook")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @PutMapping("/update")
    public ResponseEntity<ItemResponse> updateItem(
            @Valid @RequestBody ItemRequest itemRequest,
            @RequestHeader(name = "access-token") String accessToken,
            @RequestHeader(name = "realmId") Long realmId
    ) throws IOException {
        final ItemResponse itemResponse = itemService.update(itemRequest, new HeaderPayload(accessToken, realmId), "item", "Item", ItemResponse.class);
        return ResponseEntity.ok(itemResponse);
    }
}
