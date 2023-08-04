package com.dft.veracross;

import com.dft.veracross.credentials.AuthCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.parents.Parent;
import com.dft.veracross.model.parents.ParentWrapper;
import com.dft.veracross.model.parents.ParentsWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ParentAPI extends VeracrossSDK {

    public ParentAPI(AuthCredentials credentials) {
        super(credentials);
    }

    public ParentWrapper getParentById(Integer id, String xValueList) {
        URI uri = baseUrl(PARENTS_ENDPOINT + FORWARD_SLASH_CHARACTER + id);

        HttpRequest request = get(uri, xValueList);
        HttpResponse.BodyHandler<ParentWrapper> handler = new JsonBodyHandler<>(ParentWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public ParentsWrapper getAllParent(String xValueList) {
        List<Parent> parents = new ArrayList<>();
        URI uri = baseUrl(PARENTS_ENDPOINT);

        HttpRequest request = get(uri, xValueList);
        HttpResponse.BodyHandler<ParentsWrapper> handler = new JsonBodyHandler<>(ParentsWrapper.class);

        int iPage = 1;
        ParentsWrapper parentsWrapper;
        while (true) {

            parentsWrapper =  getRequestWrapped(request, handler);
            if(parentsWrapper.getData().isEmpty()) {
                parentsWrapper.setData(parents);
                return parentsWrapper;
            }
            parents.addAll(parentsWrapper.getData());

            request = get(uri, ++iPage, 1000, xValueList);
        }

    }
}
