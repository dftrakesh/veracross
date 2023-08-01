package com.dft.veracross;

import com.dft.veracross.credentials.AuthCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.parents.Parent;
import com.dft.veracross.model.parents.ParentsWrapper;
import com.dft.veracross.model.students.Student;
import com.dft.veracross.model.students.StudentsInfo;
import com.dft.veracross.model.students.StudentsWrapper;
import com.dft.veracross.model.students.StudentWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentAPI extends VeracrossSDK {

    public StudentAPI(AuthCredentials credentials) {
        super(credentials);
    }

    public StudentsInfo getStudentById(Integer id) {

        URI uri = baseUrl(STUDENTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(id.toString()));

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<StudentWrapper> handler = new JsonBodyHandler<>(StudentWrapper.class);
        return getRequestWrapped(request, handler).getData();
    }

    public List<StudentsInfo> getAllStudent() {

        List<StudentsInfo> listStudents = new ArrayList<>();
        URI uri = baseUrl(STUDENTS_ENDPOINT);

        HttpRequest request = get(uri);
        HttpResponse.BodyHandler<StudentsWrapper> handler = new JsonBodyHandler<>(StudentsWrapper.class);

        int iPage = 1;
        while (true) {

            StudentsWrapper studentsWrapper =  getRequestWrapped(request, handler);
            if(studentsWrapper.getData().isEmpty()) break;
            listStudents.addAll(studentsWrapper.getData());
            request = get(uri, ++iPage, 1000);
        }
        return listStudents;
    }
}
