package com.dft.veracross.constantcode;

public interface ConstantCodes {

    String HTTP_OAUTH_PARAMETER_GRANT_TYPE = "grant_type";
    String HTTP_OAUTH_PARAMETER_REFRESH_TOKEN = "refresh_token";
    String HTTP_OAUTH_PARAMETER_CLIENT_ID = "client_id";
    String HTTP_OAUTH_PARAMETER_CLIENT_SECRET = "client_secret";
    String REFRESH_TOKEN = "refresh_token";
    String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    String HTTP_HEADER_ACCEPTS = "Accept";
    String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    String HTTP_HEADER_VALUE_APPLICATION_JSON = "application/json";
    String HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED = "application/x-www-form-urlencoded";

    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 15000;
}