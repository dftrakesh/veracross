package com.dft.veracross.credentials;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
public class AuthCredentials {

    private String clientId;
    private String clientSecret;
    private String environment;
    private String scope;

    public AuthCredentials(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.environment = "";
        this.scope = "students:list parents:list relationships:list relationships:read";
    }

    public AuthCredentials(String clientId, String clientSecret, String environment) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.environment = environment;
        this.scope = "students:list parents:list relationships:list relationships:read";
    }

    public AuthCredentials(String clientId, String clientSecret, String environment, String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.environment = environment;
        this.scope = scope;
    }
}
