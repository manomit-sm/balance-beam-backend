package com.quickbook.playground.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDetails {

    private String email;

    private String clientId;

    private String clientSecret;

    private String redirectUrl;
}
