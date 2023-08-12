package cs5834.luckyPaws.respository;

import cs5834.luckyPaws.entity.EventParticipants;
import cs5834.luckyPaws.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventParticipantRepository extends JpaRepository<EventParticipants, Long> {
    List<EventParticipants> findById(long event_id);

}
