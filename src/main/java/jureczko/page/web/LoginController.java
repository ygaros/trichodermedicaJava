package jureczko.page.web;

import jureczko.page.exceptions.PasswordIncorectException;
import jureczko.page.exceptions.UsernameAlreadyExists;
import jureczko.page.response.LoginRequest;
import jureczko.page.response.LoginResponse;
import jureczko.page.security.SecurityCipher;
import jureczko.page.security.UserDTO;
import jureczko.page.service.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> getLoginPage(
            @Valid @RequestBody LoginRequest loginRequest){
        return loginService.signIn(loginRequest);
    }

    @GetMapping("/refresh")
    public String refreshToken(
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            HttpServletResponse response) {
        String forward = response.getHeader("alicja");
        refreshToken = SecurityCipher.decrypt(refreshToken);
        response.addHeader(HttpHeaders.SET_COOKIE, loginService.refresh(refreshToken));
        return "forward:"+forward;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> getRegister(@Valid @RequestBody UserDTO userDTO)throws UsernameAlreadyExists, PasswordIncorectException, MethodArgumentNotValidException {
        loginService.signUp(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/logout")
    @ResponseBody
    public ResponseEntity<Void> getLogout(){
        return loginService.signOut();
    }

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<LoginResponse> getUserDetails(
            @CookieValue(name = "accessToken", required = false) String accessToken,
            @CookieValue(name = "refreshToken", required = false) String refreshToken){
            return loginService.getUserDetails(accessToken, refreshToken);
    }
}
