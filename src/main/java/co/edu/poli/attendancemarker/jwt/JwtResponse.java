package co.edu.poli.attendancemarker.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class JwtResponse implements Serializable {
    private String token;
}
