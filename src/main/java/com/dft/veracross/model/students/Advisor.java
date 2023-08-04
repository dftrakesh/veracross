package com.dft.veracross.model.students;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Advisor {

    String name;
    Integer id;
}
