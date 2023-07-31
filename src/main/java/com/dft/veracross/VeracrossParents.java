package com.dft.veracross;

import com.dft.veracross.credentials.VeracrossCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.parents.ParentsWrapper;
import com.dft.veracross.model.parents.parentWrapper.ParentWrapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class VeracrossParents extends VeracrossSDK {

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String PARENTS_ENDPOINT = "/parents";

    public VeracrossParents(VeracrossCredentials credentials) {
        super(credentials);
    }

    public ParentWrapper getParentById(Integer id, VeracrossCredentials veracrossCredentials) {
        URI uri = baseUrl(PARENTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(id.toString()));

        System.out.println("uri: " + uri);
        HttpRequest request = get(uri, veracrossCredentials);
        HttpResponse.BodyHandler<ParentWrapper> handler = new JsonBodyHandler<>(ParentWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public ParentsWrapper getAllParent(HashMap<String, String> params, VeracrossCredentials veracrossCredentials) {
        URI uri = baseUrl(PARENTS_ENDPOINT);
        uri = addParameters(uri, params);

        System.out.println("uri: " + uri);

        HttpRequest request = get(uri, veracrossCredentials);
        HttpResponse.BodyHandler<ParentsWrapper> handler = new JsonBodyHandler<>(ParentsWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
