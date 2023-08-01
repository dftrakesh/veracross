package com.dft.veracross.credentials;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class AccessCredentials {

    private String accessToken;
    private LocalDateTime expiresAt;
    private String refreshToken;
    private String tokenType;
}
