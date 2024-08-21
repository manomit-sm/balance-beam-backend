package com.quickbook.playground.services;


import com.quickbook.playground.bo.HeaderPayload;
import com.quickbook.playground.models.DeletedResponse;
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
}
