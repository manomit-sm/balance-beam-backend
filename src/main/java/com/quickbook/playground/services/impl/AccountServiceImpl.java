package com.quickbook.playground.services.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.quickbook.playground.bo.AccountPayload;
import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.exceptions.AccountException;
import com.quickbook.playground.models.AccountResponse;
import com.quickbook.playground.services.AccountService;
import com.quickbook.playground.services.QuickBook;
import com.quickbook.playground.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final QuickBook quickBook;

    private final WebClient webClient;

    private final ObjectMapper objectMapper;

    public AccountServiceImpl(
            QuickBook quickBook,
            @Value("${spring.quick-book.host}") String host,
            ObjectMapper objectMapper
    ) {
        this.quickBook = quickBook;
        webClient = WebClient.builder().baseUrl(host).build();
        this.objectMapper = objectMapper;
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public AccountResponse createAccount(AccountPayload accountPayload, HeaderPayload header) throws IOException {
        // log.info("Payload {}", objectMapper.writeValueAsString(AppUtils.createAccountObject(accountPayload)));
        Object object =  webClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/{realmId}/account")
                                .queryParam("minorversion", 73)
                                .build(header.realmId())
                )
                .accept(APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .bodyValue(objectMapper.writeValueAsString(AppUtils.createAccountObject(accountPayload)))
                .retrieve()
                .bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Account created successfully {}", response))
                .doOnError(err -> log.warn("Exception {}", err.getMessage(), err))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        return objectMapper.readerFor(AccountResponse.class).readValue(jsonNode.get("Account"));
    }

    @Override
    public List<AccountResponse> retrieveAllAccount(HeaderPayload header) throws IOException {
        final Object object = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/{realmId}/query")
                                .queryParam("query", "select * from Account")
                                .queryParam("minorversion", 73)
                                .build(header.realmId())
                ).accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .retrieve().bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Account retrieve successfully"))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        // log.info("Just Checking {}", jsonNode.get("QueryResponse").get("Account"));
        return objectMapper.readerFor(new TypeReference<List<AccountResponse>>() {
        }).readValue(jsonNode.get("QueryResponse").get("Account"));
        // log.info("Account Response List {}", accountResponseList);
    }
}
