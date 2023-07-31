package com.dft.veracross;

import com.dft.veracross.credentials.VeracrossCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.students.StudentsWrapper;
import com.dft.veracross.model.students.studentWrapper.StudentWrapper;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class VeracrossStudents extends VeracrossSDK {

    private static final String FORWARD_SLASH_CHARACTER = "/";
    private static final String STUDENTS_ENDPOINT = "/students";

    public VeracrossStudents(VeracrossCredentials credentials) {
        super(credentials);
    }

    public StudentWrapper getStudentById(Integer id, VeracrossCredentials veracrossCredentials) {

        URI uri = baseUrl(STUDENTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(id.toString()));

        System.out.println("uri: " + uri);
        HttpRequest request = get(uri, veracrossCredentials);
        HttpResponse.BodyHandler<StudentWrapper> handler = new JsonBodyHandler<>(StudentWrapper.class);
        return getRequestWrapped(request, handler);
    }

    @SneakyThrows
    public StudentsWrapper getAllStudent(HashMap<String, String> params, VeracrossCredentials veracrossCredentials) {

        URI uri = baseUrl(STUDENTS_ENDPOINT);
        uri = addParameters(uri, params);
        System.out.println("uri: " + uri);

        HttpRequest request = get(uri, veracrossCredentials);
        HttpResponse.BodyHandler<StudentsWrapper> handler = new JsonBodyHandler<>(StudentsWrapper.class);
        return getRequestWrapped(request, handler);
    }
}
