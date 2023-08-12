package cs5834.luckyPaws.controller;

import cs5834.luckyPaws.entity.Events;
import cs5834.luckyPaws.respository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin(origins = { "https://luckypaws.discovery.cs.vt.edu", "http://localhost:4000", "http://localhost:8081" })
    @RestController
    @RequestMapping("/api")
    public class EventController {
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        @Autowired
        private EventRepository eventRepository;

        @GetMapping("/events")
        public ResponseEntity<List<Events>> fetchEvents() {
            try {
                List<Events> events = new ArrayList<Events>();

                events.addAll(eventRepository.findAll());
                System.out.println(events.toString());
                if (events.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

                return new ResponseEntity<>(events, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/events/{id}")
        public ResponseEntity<Events> getEventById(@PathVariable("id") long id) {
            List<Events> eventData = eventRepository.findById(id);

            if (!eventData.isEmpty()) {
                return new ResponseEntity<>(eventData.get(0), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping("/events")
        public ResponseEntity<Events> createEvent(@RequestBody Events events) {

            try {
                Events event = eventRepository
                        .save(new Events(events.getTitle(),events.getEventDescription(), events.getEventDate(), events.getEventLocation(),
                                events.getUser_id(),timestamp2, timestamp2, events.getUpdatedUserId()));
                return new ResponseEntity<>(event, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.println(e.toString());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @PutMapping("/event/{id}")
        public ResponseEntity<Events> updateEvent(@PathVariable("id") long id, @RequestBody Events events) {
            List<Events> eventData = eventRepository.findById(id);
            if (!eventData.isEmpty()) {
                Events event = eventData.get(0);
                event.setTitle(events.getTitle());
                event.setEventDescription(events.getEventDescription());
                event.setEventDate(events.getEventDate());
                event.setEventLocation(events.getEventLocation());
                event.setUpdatedUserId(events.getUser_id());
                event.setUpdatedDate(timestamp2);
                return new ResponseEntity<>(eventRepository.save(event), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        @DeleteMapping("/event/{id}")
        public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id) {
            try {
                eventRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }



}
