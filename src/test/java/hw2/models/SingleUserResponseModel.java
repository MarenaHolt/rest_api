package hw2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserResponseModel {

    private Data data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @lombok.Data
    public static class Data {
        private int id;
        private String email;
        @JsonProperty(value = "first_name")
        private String firstName;
        @JsonProperty(value = "last_name")
        private String lastName;
        private String avatar;
    }



}
