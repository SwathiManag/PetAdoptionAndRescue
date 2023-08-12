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
@Table(name = "Event_Participants")
public class EventParticipants {
    @Id
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long participant_id;

    @Column(name = "event_id")
    private long event_id;

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    public EventParticipants(Long event_id, Long user_id, Timestamp createdDate, Timestamp updatedDate) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;

    }

}
