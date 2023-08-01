package com.dft.veracross.model.students;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentsInfo {

    public Integer id;
    public String firstName;
    public String middleName;
    public String lastName;
    public String preferredName;
    public Integer householdId;
    public Integer gender;
    public String email1;
    public Integer gradeLevel;
    public Integer schoolLevel;
    public Integer graduationYear;
    public Integer homeroom;
    public StudentsData studentData;
    public Student student;
    public String username;
    public String entryDate;
    public String exitDate;
    public String birthday;
    public String roles;
    public String lastModifiedDate;
}
