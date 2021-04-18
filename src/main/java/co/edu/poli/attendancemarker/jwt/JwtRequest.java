package co.edu.poli.attendancemarker.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtRequest implements Serializable {

    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("password")
    private String password;
}
