package com.dft.veracross.model.parents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Parent {

    Integer id;
    Integer householdId;
    Boolean headOfHousehold;
    Integer namePrefix;
    String firstName;
    String firstNickName;
    String preferredName;
    String middleName;
    String lastName;
    Integer nameSuffix;
    Spouse spouse;
    Integer gender;
    String roles;
    @JsonProperty("email_1")
    String email1;
    String username;
    String lastModifiedDate;
}
