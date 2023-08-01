package com.dft.veracross.model.token;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String scope;
    private String createdAt;
    private long expiresIn;

}
