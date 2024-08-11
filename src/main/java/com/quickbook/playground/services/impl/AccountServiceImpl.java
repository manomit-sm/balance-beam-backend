package com.quickbook.playground.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.quickbook.playground.bo.AccountPayload;
import com.quickbook.playground.exceptions.AccountException;
import com.quickbook.playground.models.AccountResponse;
import com.quickbook.playground.services.AccountService;
import com.quickbook.playground.services.QuickBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final QuickBook quickBook;

    private final String realmId;

    private final WebClient webClient;

    private final ObjectMapper objectMapper;

    public AccountServiceImpl(
            QuickBook quickBook,
            @Value("${spring.quick-book.host}") String host,
            @Value("${spring.quick-book.realm-id}") String realmId,
            ObjectMapper objectMapper
    ) {
        this.quickBook = quickBook;
        this.realmId = realmId;
        webClient = WebClient.builder().baseUrl(host).build();
        this.objectMapper = objectMapper;
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public AccountResponse createAccount(AccountPayload accountPayload) throws IOException {
        Object object =  webClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/" + Long.parseLong(realmId) + "/account")
                                .queryParam("minorversion", 73)
                                .build()
                )
                .accept(APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .headers(h -> h.setBearerAuth(quickBook.getAccessToken()))
                .bodyValue(objectMapper.writeValueAsString(createAccountObject(accountPayload)))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError ,
                        error -> {
                            log.error("Exception occurred while creating account : {}", error.bodyToMono(String.class));
                            return Mono.error(new AccountException(error.bodyToMono(String.class).block()));
                        })
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> {
                            log.error("Exception occurred while creating account : {}", error.bodyToMono(String.class));
                            return Mono.error(new AccountException(error.bodyToMono(String.class).block()));
                        })
                .bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Account created successfully {}", response))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        return objectMapper.readerFor(AccountResponse.class).readValue(jsonNode.get("Account"));
    }

    @Override
    public List<AccountResponse> retrieveAllAccount() throws IOException {
        final Object object = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/{realmId}/query")
                                .queryParam("query", "select * from Account where Name like 'B%'")
                                .queryParam("minorversion", 73)
                                .build(Long.parseLong(realmId))
                ).accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(quickBook.getAccessToken()))
                .retrieve().bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Account retrieve successfully"))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        // log.info("Just Checking {}", jsonNode.get("QueryResponse").get("Account"));
        return objectMapper.readerFor(new TypeReference<List<AccountResponse>>() {
        }).readValue(jsonNode.get("QueryResponse").get("Account"));
        // log.info("Account Response List {}", accountResponseList);
    }

    private Map<String, String> createAccountObject(AccountPayload accountPayload) {
        Map<String, String> map = new HashMap<>();
        map.put("Name", accountPayload.getName());
        map.put("AccountType", accountPayload.getAccountType());
        return map;
    }
}
