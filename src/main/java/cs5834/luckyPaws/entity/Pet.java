package cs5834.luckyPaws.entity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pet")
public class Pet {

    public Pet(String pet_name, String pet_type, String breed, String flag) {
        // TODO Auto-generated constructor stub
        this.pet_name = pet_name;
        this.petType = pet_type;
        this.breed = breed;
        this.flag = flag;
    }

    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "pet_name")
    private String pet_name;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "location")
    private String location;

    @Column(name = "breed")
    private String breed;

    @Column(name = "age")
    private Integer age;

    @Column(name = "description")
    private String description;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "flag")
    private String flag; //(adoption,missing,found,rescue)

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "created_date")
    private Timestamp created_date;

    @Column(name = "updated_date")
    private Timestamp updated_date;

    @Column(name = "pet_image")
    private Long petImage;

    @Column(name = "archive_flag")
    private Integer archive_flag;

    private  String reqFlag;

    @Column(name = "updated_user_id")
    private long updated_user_id;

    public Pet(String pet_name, String petType, String location, String breed, Integer age, String flag,
               String description, String size, String color, String reqFlag, Timestamp createdDate, Timestamp updateDate,
               Long petImage, int archiveFlag, long userId, long updated_user_id) {
        this.pet_name = pet_name;
        this.petType = petType;
        this.location = location;
        this.breed = breed;
        this.age = age;
        this.flag = flag;
        this.description = description;
        this.size = size;
        this.color = color;
        this.reqFlag = reqFlag;
        this.created_date = createdDate;
        this.updated_date = updateDate;
        this.petImage = petImage;
        this.archive_flag = archiveFlag;
        this.userID = userId;
        this.updated_user_id = updated_user_id;
    }
}
