package cs5834.luckyPaws.respository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cs5834.luckyPaws.entity.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByPetType(String petType);
    List<Pet> findByBreed(String breed);


    @Query("SELECT p FROM Pet p WHERE p.flag = ?1")
    List<Pet> findByFlag(String flag);

    @Query(value = "SELECT p.id,p.pet_name, p.petType, p.breed,p.age,p.location,p.description, u.firstName,"+
            "p.created_date,p.reqFlag,p.petImage,u.user_id FROM Pet p join p.user u  WHERE  p.flag = :flag")
    List<Object> findPetByFlag(String flag);

    @Query("SELECT p FROM Pet p WHERE p.id = ?1")
    List<Pet> updatePetReqFlag(long id);

    @Query(value = "SELECT p.id,p.pet_name, p.petType, p.breed,p.age,p.location,p.description, u.firstName,"+
            "p.created_date,p.reqFlag,p.petImage,p.flag FROM Pet p join p.user u  WHERE  p.petType = :searchParam OR " +
            "p.breed = :searchParam")
    List<Object> searchPet(String searchParam);

    List<Object> findById(long pet_id);
    @Query("SELECT p FROM Pet p WHERE p.userID = ?1")
    List<Object> findByUserId(long id);

    @Query("SELECT p.id FROM Pet p WHERE p.userID = ?1")
    List<Long> getPetIdByUserId(long id);

    @Modifying
    @Query("DELETE FROM Pet p WHERE p.userID in (:id)")
    Boolean deletePetByPetId(List<Long> id);


}
