package jureczko.page.response;

import jureczko.page.security.Authority;

import java.util.List;

public class LoginResponse {
    private String username;
    private List<Authority> authorities;

    public LoginResponse(String username, List<Authority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
