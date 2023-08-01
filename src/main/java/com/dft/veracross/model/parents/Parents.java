package com.dft.veracross.model.parents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Parents {

    public Integer id;
    public Integer householdId;
    public Boolean headOfHousehold;
    public Integer namePrefix;
    public String firstName;
    public String firstNickName;
    public String preferredName;
    public String middleName;
    public String lastName;
    public Integer nameSuffix;
    public Spouse spouse;
    public Integer gender;
    public String roles;
    public String email1;
    public String username;
    public String lastModifiedDate;
}
