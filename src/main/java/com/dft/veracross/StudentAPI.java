package com.dft.veracross;

import com.dft.veracross.credentials.AuthCredentials;
import com.dft.veracross.handler.JsonBodyHandler;
import com.dft.veracross.model.common.ValueList;
import com.dft.veracross.model.students.StudentWrapper;
import com.dft.veracross.model.students.StudentsInfo;
import com.dft.veracross.model.students.StudentsWrapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class StudentAPI extends VeracrossSDK {

    public StudentAPI(AuthCredentials credentials) {
        super(credentials);
    }

    public StudentWrapper getStudentById(Integer id) {

        URI uri = baseUrl(STUDENTS_ENDPOINT.concat(FORWARD_SLASH_CHARACTER)
                .concat(id.toString()));

        HttpRequest request = get(uri, "include");
        HttpResponse.BodyHandler<StudentWrapper> handler = new JsonBodyHandler<>(StudentWrapper.class);
        return getRequestWrapped(request, handler);
    }

    public StudentsWrapper getAllStudent() {

        List<StudentsInfo> listStudents = new ArrayList<>();
        List<ValueList> fieldValueList = new ArrayList<>();
        URI uri = baseUrl(STUDENTS_ENDPOINT);

        HttpRequest request = get(uri, "include");
        HttpResponse.BodyHandler<StudentsWrapper> handler = new JsonBodyHandler<>(StudentsWrapper.class);

        int iPage = 1;
        StudentsWrapper studentsWrapper;
        while (true) {

            studentsWrapper = getRequestWrapped(request, handler);
            if (studentsWrapper.getData().isEmpty()) break;

            listStudents.addAll(studentsWrapper.getData());
            fieldValueList.addAll(studentsWrapper.getValueLists());

            request = get(uri, ++iPage, 1000, "include");
        }
        studentsWrapper.setData(listStudents);
        studentsWrapper.setValueLists(fieldValueList);

        return studentsWrapper;
    }
}
