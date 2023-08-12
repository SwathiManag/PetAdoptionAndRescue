package cs5834.luckyPaws.controller;

import cs5834.luckyPaws.entity.EventParticipants;
import cs5834.luckyPaws.entity.Events;
import cs5834.luckyPaws.entity.Pet;
import cs5834.luckyPaws.respository.EventParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "https://luckypaws.discovery.cs.vt.edu", "http://localhost:4000", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class EventParticipantController {

    Date date = new Date();
    Timestamp timestamp2 = new Timestamp(date.getTime());
    @Autowired
    private EventParticipantRepository eventParticipantRepository;

    @PostMapping("/events/eventParticipant")
    public ResponseEntity<EventParticipants> createEventParticipants(@RequestBody EventParticipants eventParticipants) {

        try {
            EventParticipants eventParticipant = eventParticipantRepository.save(new EventParticipants(eventParticipants.getEvent_id(),
                    eventParticipants.getUser_id(),timestamp2, timestamp2));
            return new ResponseEntity<>(eventParticipant, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
