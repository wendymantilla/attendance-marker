package co.edu.poli.attendancemarker.controllers;

import co.edu.poli.attendancemarker.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil tokenUtil;

    @GetMapping("/user")
    public ResponseEntity<UserDetails> getUserName(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String userName = tokenUtil.getUsernameFromToken(token);
        UserDetails jwtUser = userDetailsService.loadUserByUsername(userName);
        return ResponseEntity.ok().body(jwtUser);

    }
}
