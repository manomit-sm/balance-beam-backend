package com.quickbook.playground.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.models.DeletedResponse;
import com.quickbook.playground.models.TransactionListResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public interface GenericService <T, R> {
    R create(T t, HeaderPayload header, String table, String rootObject, Class<R> clazz) throws IOException;
    List<R> list(HeaderPayload header, String table, String rootObject) throws IOException;

    R getById(HeaderPayload header, String id, String table, String rootObject, Class<R> clazz) throws IOException;

    R update(T t, HeaderPayload header, String table, String rootObject, Class<R> clazz) throws IOException;

    DeletedResponse delete(T t, HeaderPayload header, String table, String rootObject) throws IOException;

    default Mono<byte[]> generatePdfFromId(HeaderPayload header, String id, String table) {
        return null;
    }

    default Object sendEmailFromId(HeaderPayload header, String id, String table, String toEmail) {
        return null;
    }

    default List<TransactionListResponse> getTransactionList(HeaderPayload header, String startDate, String endDate) throws IOException {
        return null;
    }
    default Object getBalanceSheet(HeaderPayload header, String startDate, String endDate) {
        return null;
    }

    default Object getCashFlow(HeaderPayload header, String startDate, String endDate) {
        return null;
    }

    default Object getProfitAndLoss(HeaderPayload header, String startDate, String endDate) {
        return null;
    }

}
