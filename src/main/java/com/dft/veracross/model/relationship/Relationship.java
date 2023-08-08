package com.dft.veracross.model.relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Relationship {
    String id;
    String personId;
    String relatedPersonId;
    String firstName;
    String middleName;
    String lastName;
    String preferredName;
    String relationship;
}