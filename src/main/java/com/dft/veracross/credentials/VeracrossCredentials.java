package com.dft.veracross.credentials;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class VeracrossCredentials {

    private String accessToken;
    private String sandbox;
}
