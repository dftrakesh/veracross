package com.dft.veracross;

import com.dft.veracross.constantcode.ConstantCodes;
import com.dft.veracross.credentials.AccessCredentials;
import com.dft.veracross.credentials.AuthCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.parents.Parent;
import com.dft.veracross.model.parents.ParentsWrapper;
import com.dft.veracross.model.students.StudentsInfo;
import com.dft.veracross.model.students.StudentsWrapper;
import com.dft.veracross.model.token.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class VeracrossSDK {

    public static final String FORWARD_SLASH_CHARACTER = "/";
    public static final String PARENTS_ENDPOINT = "/parents";
    public static final String STUDENTS_ENDPOINT = "/students";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASE_ENDPOINT = "https://api.veracross.com/";
    private static final String VERSION_3 = "/v3";
    int MAX_ATTEMPTS = 100;
    int TIME_OUT_DURATION = 1700;

    protected String baseUrl;
    private final HttpClient client;
    private final AuthCredentials credentials;
    private AccessCredentials accessCredentials;

    public VeracrossSDK(AuthCredentials credentials) {
        this.credentials = credentials;
        this.baseUrl = BASE_ENDPOINT + credentials.getEnvironment();
        client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        accessToken();
    }


    protected URI addParameters(URI uri, HashMap<String, String> params) {
        String query = uri.getQuery();
        StringBuilder builder = new StringBuilder();

        if (query != null)
            builder.append(query);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String keyValueParam = entry.getKey() + "=" + entry.getValue();
            if (!builder.toString().isEmpty())
                builder.append("&");
            builder.append(keyValueParam);
        }
        return URI.create(uri.toString() + builder);
    }

    protected URI baseUrl(String path) {
        return URI.create(baseUrl + VERSION_3 + path);
    }

    @SneakyThrows
    protected void refreshAccessToken() {
        if (LocalDateTime.now(ZoneOffset.UTC).isAfter(accessCredentials.getExpiresAt())) {
            Map<Object, Object> data = new HashMap<>();
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_GRANT_TYPE, ConstantCodes.REFRESH_TOKEN);
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_REFRESH_TOKEN, accessCredentials.getRefreshToken());
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_ID, credentials.getClientId());
            data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_SECRET, credentials.getClientSecret());

            HttpRequest request = HttpRequest.newBuilder(new URI("https://accounts.veracross.com/" + credentials.getEnvironment() + "/oauth/token"))
                    .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED)
                    .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                    .POST(ofFormData(data))
                    .build();

            AccessTokenResponse accessTokenResponse = HttpClient.newHttpClient()
                    .send(request, new JsonBodyHandler<>(AccessTokenResponse.class))
                    .body();

            accessCredentials.setAccessToken(accessTokenResponse.getAccessToken());
            accessCredentials.setRefreshToken(accessTokenResponse.getRefreshToken());
            accessCredentials.setExpiresAt(LocalDateTime.now(ZoneOffset.UTC).plusSeconds(accessTokenResponse.getExpiresIn()));
            accessCredentials.setTokenType(accessTokenResponse.getTokenType());
        }
    }

    @SneakyThrows
    protected void accessToken() {
        Map<Object, Object> data = new HashMap<>();
        data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_GRANT_TYPE, ConstantCodes.GRANT_TYPE_CLIENT_CREDENTIALS);
        data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_ID, credentials.getClientId());
        data.put(ConstantCodes.HTTP_OAUTH_PARAMETER_CLIENT_SECRET, credentials.getClientSecret());
        data.put("scope", credentials.getScope());

        HttpRequest request = HttpRequest.newBuilder(new URI("https://accounts.veracross.com/" + credentials.getEnvironment() + "/oauth/token"))
                .header(ConstantCodes.HTTP_HEADER_CONTENT_TYPE, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_FORM_URL_ENCODED)
                .header(ConstantCodes.HTTP_HEADER_ACCEPTS, ConstantCodes.HTTP_HEADER_VALUE_APPLICATION_JSON)
                .POST(ofFormData(data))
                .build();

        AccessTokenResponse accessTokenResponse = HttpClient.newHttpClient()
                .send(request, new JsonBodyHandler<>(AccessTokenResponse.class))
                .body();

        accessCredentials = new AccessCredentials();
        accessCredentials.setAccessToken(accessTokenResponse.getAccessToken());
        accessCredentials.setRefreshToken(accessTokenResponse.getRefreshToken());
        accessCredentials.setExpiresAt(LocalDateTime.now(ZoneOffset.UTC).plusSeconds(accessTokenResponse.getExpiresIn()));
        accessCredentials.setTokenType(accessTokenResponse.getTokenType());

    }

    public HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    protected HttpRequest get(URI uri) {
        refreshAccessToken();
        return HttpRequest.newBuilder(uri)
                .header(AUTHORIZATION, accessCredentials.getAccessToken())
                .GET()
                .build();
    }

    protected HttpRequest get(URI uri, int iPage) {
        refreshAccessToken();
        return get(uri, iPage, 100);
    }

    protected HttpRequest get(URI uri, int iPage, int iSize) {
        refreshAccessToken();
        return HttpRequest.newBuilder(uri)
                .header(AUTHORIZATION, accessCredentials.getAccessToken())
                .header("X-Page-Number", String.valueOf(iPage))
                .header("X-Page-Size", String.valueOf(iSize))
                .GET()
                .build();
    }

    @SneakyThrows
    public <T> T getRequestWrapped(HttpRequest request, HttpResponse.BodyHandler<T> handler) {

        return client
                .sendAsync(request, handler)
                .thenComposeAsync(response -> tryResend(client, request, handler, response, 1))
                .get()
                .body();
    }

    @SneakyThrows
    public <T> CompletableFuture<HttpResponse<T>> tryResend(HttpClient client,
                                                            HttpRequest request,
                                                            HttpResponse.BodyHandler<T> handler,
                                                            HttpResponse<T> resp, int count) {
        if (resp.statusCode() == 429 && count < MAX_ATTEMPTS) {
            long lLimitResetSeconds = resp.headers().firstValueAsLong("x-rate-limit-reset").orElse(TIME_OUT_DURATION);
            Thread.sleep(lLimitResetSeconds * 1000);
            HttpResponse<T> response = client.send(request, handler);
            return tryResend(client, request, handler, response, count + 1);
        }
        return CompletableFuture.completedFuture(resp);
    }
}
