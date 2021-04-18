package co.edu.poli.attendancemarker.services;

import co.edu.poli.attendancemarker.jpa.repository.UserRepository;
import co.edu.poli.attendancemarker.jwt.JwtUserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByUserName(username)
                .map(user -> JwtUserFactory.jwtUser(user))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
