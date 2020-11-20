package jureczko.page.objectsDTO;

public class KontaktDTO {
    private String email;
    private String phone;

    public KontaktDTO() {
    }
    public KontaktDTO(String email, String phone){
        this();
        this.email = email;
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
