package jureczko.page.service;

import jureczko.page.data.AuthorityRepository;
import jureczko.page.data.UserRepository;
import jureczko.page.exceptions.AccessTokenExpired;
import jureczko.page.exceptions.PasswordIncorectException;
import jureczko.page.exceptions.UsernameAlreadyExists;
import jureczko.page.response.LoginRequest;
import jureczko.page.response.LoginResponse;
import jureczko.page.security.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final CookieUtil cookieUtil;

    public LoginService(UserRepository userRepository,
                        AuthorityRepository authorityRepository,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager,
                        JwtProvider jwtProvider,
                        CookieUtil cookieUtil) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.cookieUtil = cookieUtil;
    }

    public ResponseEntity<LoginResponse> signIn(LoginRequest loginRequest) throws IllegalArgumentException{
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = loginRequest.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new IllegalArgumentException("Użytkownik o o adresie email -> '" + username+"' nie istnieje"));
        List<Authority> authorities = authorityRepository.findByUsername(username);

        HttpHeaders responseHeaders = new HttpHeaders();
        Token newAccessToken = jwtProvider.generateAccessToken(user.getUsername(), authorities);
        Token newRefreshToken = jwtProvider.generateRefreshToken(user.getUsername(), authorities);
        addAccessTokenCookie(responseHeaders, newAccessToken);
        addRefreshTokenCookie(responseHeaders, newRefreshToken);

        LoginResponse loginResponse = new LoginResponse(username, authorities);
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    public String refresh(String refreshToken) throws IllegalArgumentException{
        boolean refreshTokenValid = jwtProvider.isValidToken(refreshToken);
        if (!refreshTokenValid) {
            throw new IllegalArgumentException("Zaloguj się ponownie w celu autentykacji konta");
        }
        String username = jwtProvider.getUsername(refreshToken);
        List<Authority> authorities = authorityRepository.findByUsername(username);
        Token newAccessToken = jwtProvider.generateAccessToken(username, authorities);

        return cookieUtil.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration()).toString();
    }

    public Optional<User> signUp(UserDTO userDTO) throws UsernameAlreadyExists, PasswordIncorectException {
        if (!userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            User user = userRepository.save(userDTO.toUser(passwordEncoder));
            authorityRepository.save(new Authority("ROLE_USER", userDTO.getUsername()));
            return Optional.of(user);
        }
        throw new UsernameAlreadyExists("Email "+userDTO.getUsername()+" jest zajęty!");
    }
    public ResponseEntity<Void> signOut(){
        HttpHeaders header = new HttpHeaders();

        header.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteAccessTokenCookie().toString());
        header.add(HttpHeaders.SET_COOKIE, cookieUtil.deleteRefreshTokenCookie().toString());
        return new ResponseEntity<>(header, HttpStatus.OK);

    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }
    private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }
    private String deCrypt(String token){
        return SecurityCipher.decrypt(token);
    }

    public ResponseEntity<LoginResponse> getUserDetails(String accessToken, String refreshToken){
        accessToken = deCrypt(accessToken);
        refreshToken = deCrypt(refreshToken);
        String token = "";
        if(null!=accessToken) token = accessToken;
        else if (null!=refreshToken) token = refreshToken;
        else throw new AccessTokenExpired("Prosze o ponowne zalogowanie się do systemu");
        String username = jwtProvider.getUsername(token);
        return new ResponseEntity<>(new LoginResponse(username,
                authorityRepository.findByUsername(username)),
                HttpStatus.OK);
    }
}
