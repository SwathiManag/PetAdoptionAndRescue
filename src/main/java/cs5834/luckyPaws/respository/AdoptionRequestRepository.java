package cs5834.luckyPaws.respository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import cs5834.luckyPaws.entity.Pet;
import cs5834.luckyPaws.entity.AdoptionRequest;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    List<AdoptionRequest> findByUserID(long userID);
    
    @Query("SELECT req.requestID, req.petID, req.userID, req.status, req.createdDate "
        +  "FROM AdoptionRequest req, Pet p "
        +  "WHERE p.id = req.petID and p.userID = ?1")
    List<AdoptionRequest> findByReceiverID(long userID);
    
    
    @Query("SELECT p FROM Pet p WHERE p.id NOT IN (SELECT petID FROM AdoptionRequest)"
        + " and p.userID != :userID")
    List<Pet> findAllAvailablePets(Long userID);
    
    
}
