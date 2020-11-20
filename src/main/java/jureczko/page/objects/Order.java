package jureczko.page.objects;

import jureczko.page.security.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;


@Entity
@Table(name = "order_table")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Duration czasTrwania;

    @ManyToOne
    private User user;


    public Duration getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(Duration czasTrwania) {
        this.czasTrwania = czasTrwania;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order() {
    }

    public Order(User user, Duration czasTrwania) {
        this();
        this.user = user;
        this.czasTrwania = czasTrwania;
    }
}
