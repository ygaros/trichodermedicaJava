package jureczko.page.objects;


import javax.persistence.*;

@Entity
public class Proza {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    @Column(length = 100000)
    private String tresc;

    public Proza(){

    }
    public Proza(String nazwa, String tresc){
        this();
        this.nazwa = nazwa;
        this.tresc = tresc;
    }

    public Long getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
