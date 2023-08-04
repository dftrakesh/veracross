package com.dft.veracross.model.students;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class StudentsInfo {

    Integer id;
    String firstName;
    String middleName;
    String lastName;
    String preferredName;
    Integer householdId;
    Integer gender;
    @JsonProperty("email_1")
    String email1;
    String gradeLevel;
    Integer schoolLevel;
    String graduationYear;
    Integer homeroom;
    StudentsData studentData;
    Student student;
    String username;
    String entryDate;
    String exitDate;
    String birthday;
    String roles;
    String lastModifiedDate;
}
