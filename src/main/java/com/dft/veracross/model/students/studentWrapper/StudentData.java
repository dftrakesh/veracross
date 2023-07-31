package com.dft.veracross.model.students.studentWrapper;

import com.dft.veracross.model.students.StudentsInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentData {

    public Integer id;
    public Integer namePrefix;
    public String firstName;
    public Object middleName;
    public String lastName;
    public Integer nameSuffix;
    public Object preferredName;
    public Integer householdId;
    public String email1;
    public String birthday;
    public Integer gender;
    public Integer schoolLevel;
    public Integer gradeLevel;
    public Integer graduationYear;
    public Integer homeroom;
    public StudentInfo studentInfo;
    public Advisor advisor;
    public String currentLocation;
    public Integer campus;
    public Object username;
    public Object entryDate;
    public Object exitDate;
    public String lastModifiedDate;
}
