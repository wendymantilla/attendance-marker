package co.edu.poli.attendancemarker.jwt;

import co.edu.poli.attendancemarker.jpa.model.Authority;
import co.edu.poli.attendancemarker.jpa.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser jwtUser(User user) {

        return JwtUser
                .builder()
                .email(user.getEmail())
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .grantedAuthorities(mapAuthorities(user.getAuthorities()))
                .build();
    }

    private static List<GrantedAuthority> mapAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
