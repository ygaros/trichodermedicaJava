package jureczko.page.security;

import jureczko.page.data.AuthorityRepository;
import jureczko.page.data.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserPrincipleUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authRepository;
    private final JwtProvider jwtProvider;

    public UserPrincipleUserDetailsService(UserRepository userRepository,
                                           AuthorityRepository authRepository,
                                           JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);
        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException("Użytkownik '"+username+"' nie istnieje");
        }
        List<Authority> authorities = this.getCustomAuthorities(username);
        Set<SimpleGrantedAuthority> auth = new HashSet<>();
        authorities.forEach(s -> {
            auth.add(new SimpleGrantedAuthority(s.getAuthority()));
        });
        User user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                true, true, true, true,
                auth);

    }
    public Optional<UserDetails> loadUserByJwtToken(String jwtToken) {
        if (jwtProvider.isValidToken(jwtToken)) {
            return Optional.of(
                    withUsername(jwtProvider.getUsername(jwtToken))
                            .authorities(jwtProvider.getRoles(jwtToken))
                            .password("") //token does not have password but field may not be empty
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build());
        }
        return Optional.empty();
    }
    public List<Authority> getCustomAuthorities(String username){
        return this.authRepository.findByUsername(username);
    }
}
