package cs5834.luckyPaws.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cs5834.luckyPaws.entity.AdoptionRequest;
import cs5834.luckyPaws.entity.Pet;
import cs5834.luckyPaws.respository.AdoptionRequestRepository;
import cs5834.luckyPaws.respository.PetRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AdoptionController extends UserInfoBaseController{

    
    @Autowired
    private AdoptionRequestRepository adoptionRepo;
    
    
    @GetMapping("/adoption")
    ResponseEntity<List<Pet>> getAllAvailablePets() {
        try {
              List<Pet> pets = adoptionRepo.findAllAvailablePets(this.user.getUser_id());
              
              return new ResponseEntity<>(pets, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/adoption/{sender_id}")
    ResponseEntity<List<AdoptionRequest>> getAllSentRequests(@PathVariable Long sender_id) {
        try {
              List<AdoptionRequest> reqs = adoptionRepo.findByUserID(sender_id);
              
              return new ResponseEntity<>(reqs, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/adoption/{receiver_id}")
    ResponseEntity<List<AdoptionRequest>> getAllReceivedRequests(@PathVariable Long receiver_id) {
        try {
              List<AdoptionRequest> reqs = adoptionRepo.findByReceiverID(receiver_id);
              
              return new ResponseEntity<>(reqs, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("/adoption/{id}")
    ResponseEntity<AdoptionRequest> acceptAdoption(@RequestBody AdoptionRequest req, @PathVariable Long id) {
        try {
              Optional<AdoptionRequest> oldReq = adoptionRepo.findById(id);
              oldReq.get().setStatus("approved");
              
              AdoptionRequest newReq = adoptionRepo.save(oldReq.get());
              return new ResponseEntity<>(newReq, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/adoption/{id}")
    void cancelAdoption(@PathVariable Long id) {
        adoptionRepo.deleteById(id);
    }
    
    @PostMapping("/adoption")
    public ResponseEntity<AdoptionRequest> adoptPet(@RequestBody Pet pet) {
      try {
          
          AdoptionRequest req = new AdoptionRequest();
          req.setPetID(pet.getId());
          req.setUserID( this.user.getUser_id());
          req.setStatus("pending");
          req.setCreatedDate(Date.from(Instant.now()));
          
          AdoptionRequest _request = adoptionRepo.save(req);
          return new ResponseEntity<>(_request, HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
}
