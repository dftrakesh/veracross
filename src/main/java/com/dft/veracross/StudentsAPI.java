package com.dft.veracross;

import com.dft.veracross.credentials.VeracrossCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.students.StudentsWrapper;
import com.dft.veracross.model.students.studentWrapper.StudentWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class StudentsAPI extends VeracrossSDK {

    public StudentsAPI(VeracrossCredentials credentials) {
        super(credentials);
    }

    public StudentWrapper getStudentById(Integer id) throws ExecutionException, InterruptedException {

        URI uri = baseUrl(STUDENTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(id.toString()));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<StudentWrapper> handler = new JsonBodyHandler<>(StudentWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public StudentsWrapper getAllStudent(HashMap<String, String> params) throws ExecutionException, InterruptedException {

        URI uri = baseUrl(STUDENTS_ENDPOINT);
        uri = addParameters(uri, params);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<StudentsWrapper> handler = new JsonBodyHandler<>(StudentsWrapper.class);
        return getRequestWrapped(request, handler);
    }
}