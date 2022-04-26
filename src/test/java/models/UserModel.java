package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "job")
    private String job;
}
