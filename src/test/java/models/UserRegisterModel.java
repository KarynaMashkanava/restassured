package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterModel {
    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;
}
