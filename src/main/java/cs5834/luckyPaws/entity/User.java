package cs5834.luckyPaws.entity;

import lombok.*;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    
    private String emailId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private long phoneNumber;

    private String address;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name =  "role")
    private String role;

    @Column(name =  "security_ques")
    private String securityQues;
    
    public User(String firstName, String lastName, String emailId, String userName, String password, long phoneNumber,
                String address, String role, Timestamp createdDate, Timestamp updatedDate, String securityQues) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.role = role;
        this.securityQues = securityQues;
    }
}
