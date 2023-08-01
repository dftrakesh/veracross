package com.dft.veracross.model.parents.parentWrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    public String address1;
    public String address2;
    public String address3;
    public String city;
    public String stateProvince;
    public Integer postalCode;
    public String country;
}
