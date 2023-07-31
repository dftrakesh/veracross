package com.dft.veracross;

import com.dft.veracross.credentials.VeracrossCredentials;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public class VeracrossSDK {

    private final VeracrossCredentials credentials;
    int MAX_ATTEMPTS = 50;
    int TIME_OUT_DURATION = 60000;
    private final HttpClient client;
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASE_ENDPOINT = "https://api.veracross.com/api-sandbox/v3";

    public VeracrossSDK(VeracrossCredentials credentials) {
        this.credentials = credentials;
        client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    @SneakyThrows
    protected URI addParameters(URI uri, HashMap<String, String> params) {
        StringBuilder queryString = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String keyValueParam = entry.getKey() + "=" + entry.getValue();
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(keyValueParam);
        }

        String pathWithQueryString = uri.getPath();
        if (queryString.length() > 0) {
            pathWithQueryString += "?" + queryString.toString();
        }

        return new URI(uri.getScheme(), uri.getAuthority(), pathWithQueryString, uri.getFragment());
    }

    @SneakyThrows
    protected URI baseUrl(String path) {
        return new URI(new StringBuilder()
                .append(BASE_ENDPOINT)
                .append(path)
                .toString());
    }

    @SneakyThrows
    protected HttpRequest get(URI uri, VeracrossCredentials veracrossCredentials) {
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
            Thread.sleep(TIME_OUT_DURATION);
            return client.sendAsync(request, handler)
                    .thenComposeAsync(response -> tryResend(client, request, handler, response, count + 1));
        }
        return CompletableFuture.completedFuture(resp);
    }

    public VeracrossParents getParentApi() {
        return new VeracrossParents(credentials);
    }

    public VeracrossStudents getStudentApi() {
        return new VeracrossStudents(credentials);
    }
}
