package com.dft.veracross;

import com.dft.veracross.credentials.VeracrossCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.parents.ParentsWrapper;
import com.dft.veracross.model.parents.parentWrapper.ParentWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Parent extends VeracrossSDK {

    public Parent(VeracrossCredentials credentials) {
        super(credentials);
    }

    public ParentWrapper getParentById(Integer id) throws ExecutionException, InterruptedException {
        URI uri = baseUrl(PARENTS_ENDPOINT + FORWARD_SLASH_CHARACTER + id);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ParentWrapper> handler = new JsonBodyHandler<>(ParentWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public ParentsWrapper getAllParent(HashMap<String, String> params) throws ExecutionException, InterruptedException {
        URI uri = baseUrl(PARENTS_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<ParentsWrapper> handler = new JsonBodyHandler<>(ParentsWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
