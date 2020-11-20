package jureczko.page.objects;

import javax.persistence.*;

@Entity
public class NavBarHref {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlPath;

    public NavBarHref() {
    }

    public NavBarHref(String name, String urlPath) {
        this();
        this.name = name;
        this.urlPath = urlPath;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
