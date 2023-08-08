package com.dft.veracross;

import com.dft.veracross.credentials.AuthCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.relationship.Relationships;
import com.dft.veracross.model.students.StudentWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RelationshipAPI extends VeracrossSDK {

    public RelationshipAPI(AuthCredentials credentials) {
        super(credentials);
    }

    public Relationships getRelationshipByPersonId(String personId, String relationShips) {

        URI uri = baseUrl(RELATIONSHIPS_ENDPOINT.concat(PERSON_ID_PARAM)
                                                .concat(personId).concat(REALTIONSHIPS).concat(relationShips));

        HttpRequest request = get(uri, "");
        HttpResponse.BodyHandler<Relationships> handler = new JsonBodyHandler<>(Relationships.class);
        return getRequestWrapped(request, handler);
    }
}