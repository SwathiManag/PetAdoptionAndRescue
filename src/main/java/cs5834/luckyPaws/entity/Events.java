package cs5834.luckyPaws.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Events")
public class Events {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long event_id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @Column(name = "event_date", nullable = false)
    private Date eventDate;

    @Column(name = "event_location", nullable = false)
    private String eventLocation;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "updated_user_id")
    private long updatedUserId;

    public Events(String title, String eventDescription, Date eventDate, String eventLocation, Long user_id,Timestamp createdDate,
                  Timestamp updatedDate, Long updatedUserId) {
        this.title = title;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.user_id = user_id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.updatedUserId = updatedUserId;
    }
}
