package com.dft.veracross.model.students.studentWrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentData {

    public Integer id;
    public Integer namePrefix;
    public String firstName;
    public String middleName;
    public String lastName;
    public Integer nameSuffix;
    public String preferredName;
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
    public String username;
    public String entryDate;
    public String exitDate;
    public String lastModifiedDate;
}
