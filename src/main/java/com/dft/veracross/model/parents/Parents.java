package com.dft.veracross.model.parents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Parents {

    public Integer id;
    public Integer householdId;
    public Boolean headOfHousehold;
    public Integer namePrefix;
    public String firstName;
    public String firstNickName;
    public Object preferredName;
    public Object middleName;
    public String lastName;
    public Integer nameSuffix;
    public Spouse spouse;
    public Integer gender;
    public String roles;
    public Object email1;
    public String username;
    public String lastModifiedDate;
}
