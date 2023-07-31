package com.dft.veracross.model.parents.parentWrapper;

import com.dft.veracross.model.parents.Spouse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ParentsData {

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
    public String username;
    public Object email1;
    public Object email2;
    public String homePhone;
    public String mobilePhone;
    public String businessPhone;
    public Address address;
    public String employer;
    public Integer occupation;
    public Object jobTitle;
    public String lastModifiedDate;
}
