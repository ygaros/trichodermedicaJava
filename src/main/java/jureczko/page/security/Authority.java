package jureczko.page.security;

import javax.persistence.*;

@Entity
public class Authority {
    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String authority;

    public Authority() {
    }
    public Authority(String authority, String username){
        this();
        this.authority = authority;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
