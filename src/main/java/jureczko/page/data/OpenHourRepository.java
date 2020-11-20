package jureczko.page.data;

import jureczko.page.objects.OpenHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;

public interface OpenHourRepository extends JpaRepository<OpenHours, String> {
    OpenHours findByDay(DayOfWeek day);
}
