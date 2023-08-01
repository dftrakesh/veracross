package com.dft.veracross.model.parents.parentWrapper;

import com.dft.veracross.model.parents.Spouse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParentsData {

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
    public String username;
    public String email1;
    public String email2;
    public String homePhone;
    public String mobilePhone;
    public String businessPhone;
    public Address address;
    public String employer;
    public String occupation;
    public String jobTitle;
    public String lastModifiedDate;
}
