package jureczko.page.objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Problem implements Serializable {
    private static final long serialVersionUID = 10L;
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String urlPath;

    @Column(length = 100000)
    private String description;

    private String name;

    @OneToOne(targetEntity = Image.class)
    private Image image;


    public Problem(){
    }
    public Problem(String description, Image image) {
        this();
        this.image = image;
        this.description = description;
    }
    public Problem(String description, Image image, String name, String urlPath){
        this(description, image);
        this.name = name;
        this.urlPath = urlPath;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", urlPart='" + urlPath + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return Objects.equals(urlPath, problem.urlPath) &&
                Objects.equals(description, problem.description) &&
                Objects.equals(name, problem.name) &&
                Objects.equals(image, problem.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlPath, description, name, image);
    }
}

