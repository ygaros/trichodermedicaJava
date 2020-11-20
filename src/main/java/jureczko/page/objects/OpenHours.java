package jureczko.page.objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class OpenHours {
    @Id
    private String day;
    private String open;
    private String close;

    public OpenHours() {
    }
    public OpenHours(String day, String open, String close){
        this();
        this.day = day;
        this.open = open;
        this.close = close;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "OpenHours{" +
                "day=" + day +
                ", open='" + open + '\'' +
                ", close='" + close + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenHours openHours = (OpenHours) o;
        return day.equals(openHours.day) &&
                Objects.equals(open, openHours.open) &&
                Objects.equals(close, openHours.close);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, open, close);
    }
}
