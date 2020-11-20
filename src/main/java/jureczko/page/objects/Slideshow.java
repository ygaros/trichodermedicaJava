package jureczko.page.objects;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Slideshow{

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToMany(targetEntity = Image.class)
    private List<Image> images = new ArrayList<Image>();



    public Slideshow(){

    }
    public Slideshow(String description, String name){
        this();
        this.description = description;
        this.name = name;
    }
    public Slideshow(String description, String name, List<Image> list){
        this(description, name);
        this.images = list;
    }

    public void setImages(List<Image> problems) {
        this.images = problems;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String opis) {
        this.description = opis;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setSlideshow(List<Image> slideshow) {
        this.images = slideshow;
    }

    public void addImage(Image problem){
        this.images.add(problem);
    }


    public String getName() {
        return name;
    }

    public void setName(String nazwa) {
        this.name = nazwa;
    }

}

