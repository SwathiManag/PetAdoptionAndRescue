package cs5834.luckyPaws.respository;
import cs5834.luckyPaws.entity.Events;
import cs5834.luckyPaws.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EventRepository extends JpaRepository<Events, Long> {

    List<Events> findById(long event_id);

}
