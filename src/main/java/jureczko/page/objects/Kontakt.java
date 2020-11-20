package jureczko.page.objects;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Kontakt {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private String zipCode;
    @NotNull
    private String street;
    @NotNull
    private String houseNumber;
    @NotNull
    private String city;
    @NotNull
    @ManyToMany(targetEntity = OpenHours.class)
    private List<OpenHours> openHours;

    public Kontakt() {
    }

    public String getEmail() {
        return email;
    }

    public List<OpenHours> getOpenHours() {
        return openHours;
    }

    public void setOpenHours(List<OpenHours> godzinyOtwarcia) {
        this.openHours = godzinyOtwarcia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String telefon) {
        this.phone = telefon;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
