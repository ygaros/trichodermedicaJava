package jureczko.page.objects;

import javax.persistence.*;

@Entity
public class Zabieg {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String category;

    private String urlPath;

    private String name;

    @Column(length = 100000)
    private String description;

    private double priceOnce;
    private double priceSeries;

    private long duration;

    @OneToOne(targetEntity = Image.class)
    private Image image;

    public Zabieg() {
    }
    public Zabieg(String name, String decription, String urlPath, long minuty, String category, Image image, double priceOnce, double priceSeries){
        this();
        this.name = name;
        this.description = decription;
        this.urlPath = urlPath;
        this.duration = minuty;
        this.category = category;
        this.image = image;
        this.priceOnce = priceOnce;
        this.priceSeries = priceSeries;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public double getPriceOnce() {
        return priceOnce;
    }

    public void setPriceOnce(double priceOnce) {
        this.priceOnce = priceOnce;
    }

    public double getPriceSeries() {
        return priceSeries;
    }

    public void setPriceSeries(double priceSeries) {
        this.priceSeries = priceSeries;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long minuty) {
        this.duration = minuty;
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

}
