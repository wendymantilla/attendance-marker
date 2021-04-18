package co.edu.poli.attendancemarker.security;

import co.edu.poli.attendancemarker.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestAuthorization = request.getHeader(AUTHORIZATION);
        String userName = "";
        String authToken = "";
        if (!Objects.isNull(requestAuthorization) && requestAuthorization.startsWith("Bearer ")) {
            authToken = requestAuthorization.substring(7);
            try {
                userName = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        if (!userName.isEmpty()) {
            if (jwtTokenUtil.validateToken(authToken)) {
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);


    }
}
