package jureczko.page.objects;


import javax.persistence.*;

/**
 * dany wiersz w tabeli cennika
 */
@Entity
public class Usluga {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private String name;
    //@Column(name = "cenaraz")
    private double priceOnce;
    //@Column(name = "cenaseria")
    private double priceSeries;

    @OneToOne(targetEntity = Zabieg.class, cascade = CascadeType.MERGE)
    private Zabieg zabieg;

    public Usluga() {
    }

    public Usluga(String name, String category, double priceOnce, double priceSeries, Zabieg zabieg) {
        this();
        this.name = name;
        this.category = category;
        this.priceOnce = priceOnce;
        this.priceSeries = priceSeries;
        this.zabieg = zabieg;
    }

    public Zabieg getZabieg() {
        return zabieg;
    }

    public void setZabieg(Zabieg zabieg) {
        this.zabieg = zabieg;
    }

    public double getPriceOnce() {
        return priceOnce;
    }

    public void setPriceOnce(double cenaRaz) {
        this.priceOnce = cenaRaz;
    }

    public double getPriceSeries() {
        return priceSeries;
    }

    public void setPriceSeries(double cenaSeria) {
        this.priceSeries = cenaSeria;
    }

    public String getName() {
        return name;
    }

    public void setName(String nazwa) {
        this.name = nazwa;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String kategoria) {
        this.category = kategoria;
    }
}
