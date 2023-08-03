package com.dft.veracross;

import com.dft.veracross.credentials.AuthCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.common.ValueList;
import com.dft.veracross.model.parents.Parent;
import com.dft.veracross.model.parents.ParentWrapper;
import com.dft.veracross.model.parents.ParentsWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<ValueList> fieldValueList = new ArrayList<>();
        URI uri = baseUrl(PARENTS_ENDPOINT);

        HttpRequest request = get(uri, xValueList);
        HttpResponse.BodyHandler<ParentsWrapper> handler = new JsonBodyHandler<>(ParentsWrapper.class);

        int iPage = 1;
        ParentsWrapper parentsWrapper;
        while (true) {

            parentsWrapper =  getRequestWrapped(request, handler);
            if(parentsWrapper.getData().isEmpty()) break;
            parents.addAll(parentsWrapper.getData());
            fieldValueList.addAll(parentsWrapper.getValueLists());

            request = get(uri, ++iPage, 1000, xValueList);
        }
        parentsWrapper.setData(parents);
        parentsWrapper.setValueLists(fieldValueList);
        return parentsWrapper;
    }
}
