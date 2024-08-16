package com.quickbook.playground.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickbook.playground.bo.AccountPayload;
import com.quickbook.playground.models.ClientDetails;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {
    private AppUtils() {}



    public static ClientDetails clientDetails(String email, ObjectMapper objectMapper) throws IOException {
        final Path path = Paths.get("src/main/resources/client-list.json");
        final String response = Files.readString(path);
        final HashMap<String, ClientDetails> clientDetails = objectMapper.readValue(response, new TypeReference<HashMap<String, ClientDetails>>() {
        });
        return clientDetails.get(email);
    }

    public static Map<String, Object> createAccountObject(AccountPayload accountPayload) {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", accountPayload.getName());
        map.put("AccountType", accountPayload.getAccountType());
        map.put("AcctNum", accountPayload.getAcctNum());
        map.put("AccountSubType", accountPayload.getAccountSubType());
        return map;
    }
}
