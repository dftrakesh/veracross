package com.dft.veracross;

import com.dft.veracross.credentials.VeracrossCredentials;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class VeracrossSDK {

    public static final String FORWARD_SLASH_CHARACTER = "/";
    public static final String PARENTS_ENDPOINT = "/parents";
    public static final String STUDENTS_ENDPOINT = "/students";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASE_ENDPOINT = "https://api.veracross.com";
    private static final String VERSION_3 = "/v3";
    int MAX_ATTEMPTS = 100;
    int TIME_OUT_DURATION = 1700;

    protected String baseUrl;
    private final HttpClient client;
    private final VeracrossCredentials credentials;

    public VeracrossSDK(VeracrossCredentials credentials) {
        this.credentials = credentials;
        this.baseUrl = BASE_ENDPOINT + FORWARD_SLASH_CHARACTER + credentials.getSandbox();
        client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
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
        return URI.create(uri.toString());
    }

    protected URI baseUrl(String path) {
        return URI.create(baseUrl + VERSION_3 + path);
    }

    protected HttpRequest get(URI uri) {
        return HttpRequest.newBuilder(uri)
                .header(AUTHORIZATION, credentials.getAccessToken())
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
