package com.demoqa.models;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {
    String userId;
    String username;
    String password;
    String token;
    String expires;
    @JsonProperty("created_date")
    String createdDate;
    String isActive;
}