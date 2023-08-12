package cs5834.luckyPaws.entity;

import lombok.*;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "adoption_request")
public class AdoptionRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", nullable = false)
    private long requestID;
    
    @Column(name = "pet_id", nullable = false)
    private long petID;
    
    @Column(name = "user_id", nullable = false)
    private long userID;
    
    private String status;
    
    @Column(name = "created_date")
    private Date createdDate;
    
}
