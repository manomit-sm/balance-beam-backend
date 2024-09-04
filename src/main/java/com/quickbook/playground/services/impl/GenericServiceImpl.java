package com.quickbook.playground.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.intuit.ipp.data.ColData;
import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.models.DeletedResponse;
import com.quickbook.playground.models.TransactionListIntermediaryResponse;
import com.quickbook.playground.models.TransactionListResponse;
import com.quickbook.playground.services.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@Slf4j
public class GenericServiceImpl<T, R> implements GenericService<T, R> {
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public GenericServiceImpl(@Value("${spring.quick-book.host}") String host, ObjectMapper objectMapper) {
        webClient = WebClient.builder().baseUrl(host).build();
        this.objectMapper = objectMapper;
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    @Override
    public R create(T t, HeaderPayload header, String table, String rootObject, Class<R> clazz) throws IOException {
        var object = webClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/{realmId}/" + table)
                                .queryParam("minorversion", 73)
                                .build(header.realmId())
                )
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .bodyValue(t)
                .retrieve()
                .bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Created successfully {}", response))
                .doOnError(err -> log.warn("Exception {}", err.getMessage(), err))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        return objectMapper.readerFor(clazz).readValue(jsonNode.get(rootObject));
    }

    @Override
    public List<R> list(HeaderPayload header, String table, String rootObject) throws IOException {
        final Object object = webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/{realmId}/query")
                                .queryParam("query", "select * from " + table)
                                .queryParam("minorversion", 73)
                                .build(header.realmId())
                ).accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .retrieve().bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("List fetched successfully"))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        return objectMapper.readerFor(new TypeReference<List<R>>() {
        }).readValue(jsonNode.get("QueryResponse").get(rootObject));
    }

    @Override
    public R getById(HeaderPayload header, String id, String table, String rootObject, Class<R> clazz) throws IOException {
        var object = webClient.get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .path("/v3/company/{realmId}/" + table + "/{id}")
                                        .queryParam("minorversion", 73)
                                        .build(header.realmId(), id)
                ).accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .retrieve().bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Retrieve by id {} successfully", id))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        return objectMapper.readerFor(clazz).readValue(jsonNode.get(rootObject));
    }

    @Override
    public R update(T t, HeaderPayload header, String table, String rootObject, Class<R> clazz) throws IOException {
        var object = webClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/{realmId}/" + table)
                                .queryParam("minorversion", 73)
                                .build(header.realmId())
                )
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .bodyValue(t)
                .retrieve()
                .bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Updated successfully {}", response))
                .doOnError(err -> log.warn("Exception {}", err.getMessage(), err))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        return objectMapper.readerFor(clazz).readValue(jsonNode.get(rootObject));
    }

    @Override
    public DeletedResponse delete(T t, HeaderPayload header, String table, String rootObject) throws IOException {
        var object = webClient.post()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/v3/company/{realmId}/" + table)
                                .queryParam("minorversion", 73)
                                .queryParam("operation", "delete")
                                .build(header.realmId())
                )
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .bodyValue(t)
                .retrieve()
                .bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Deleted successfully {}", response))
                .doOnError(err -> log.warn("Exception {}", err.getMessage(), err))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        return objectMapper.readerFor(DeletedResponse.class).readValue(jsonNode.get(rootObject));
    }

    @Override
    public Mono<byte[]> generatePdfFromId(HeaderPayload header, String id, String table) {
        return webClient.get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .path("/v3/company/{realmId}/{table}/{id}/pdf")
                                        .queryParam("minorversion", 73)
                                        .build(header.realmId(), table, id)
                )
                .accept(MediaType.APPLICATION_PDF)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .exchangeToMono(response -> response
                        .bodyToMono(ByteArrayResource.class))
                .map(ByteArrayResource::getByteArray);
    }

    @Override
    public Object sendEmailFromId(HeaderPayload header, String id, String table, String toEmail) {
        return webClient.post()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .path("/v3/company/{realmId}/{table}/{id}/send")
                                        .queryParam("minorversion", 73)
                                        .queryParam("sendTo", toEmail)
                                        .build(header.realmId(), table, id)
                )
                .accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .retrieve().bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Email send successfully {}", id))
                .block();
    }

    @Override
    public List<TransactionListResponse> getTransactionList(HeaderPayload header, String startDate, String endDate) throws IOException {
        var object = webClient.get()
                .uri(
                        uriBuilder ->
                                uriBuilder
                                        .path("/v3/company/{realmId}/reports/TransactionList")
                                        .queryParam("start_date", startDate)
                                        .queryParam("end_date", endDate)
                                        .queryParam("minorversion", 73)
                                        .build(header.realmId())
                ).accept(APPLICATION_JSON)
                .headers(h -> h.setBearerAuth(header.accessToken()))
                .retrieve().bodyToMono(Object.class)
                .doOnSuccess(response -> log.info("Transaction list"))
                .block();
        final JsonNode jsonNode = new JsonMapper().readTree(objectMapper.writeValueAsString(object));
        final List<TransactionListIntermediaryResponse> transactionList = objectMapper.readerFor(new TypeReference<List<TransactionListIntermediaryResponse>>() {
        }).readValue(jsonNode.get("Rows").get("Row"));
        transactionList.forEach(elem -> System.out.println(elem.getColData().get(0).getValue()));
        return transactionList
                .stream()
                .map(this::transformTransactionData)
                .toList();
    }

    private TransactionListResponse transformTransactionData(TransactionListIntermediaryResponse elem) {
        return new TransactionListResponse
                .Builder()
                .setTransactionDate(elem.getColData().get(0).getValue())
                .setTransactionType(elem.getColData().get(1).getValue())
                .setDocumentNumber(elem.getColData().get(2).getValue())
                .setPosting(elem.getColData().get(3).getValue())
                .setName(elem.getColData().get(4).getValue())
                .setDescription(elem.getColData().get(5).getValue())
                .setAccountName(elem.getColData().get(6).getValue())
                .setSplit(elem.getColData().get(7).getValue())
                .setAmount(elem.getColData().get(8).getValue())
                .build();
    }
}
