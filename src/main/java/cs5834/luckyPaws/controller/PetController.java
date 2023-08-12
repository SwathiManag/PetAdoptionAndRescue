package cs5834.luckyPaws.controller;

import cs5834.luckyPaws.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import cs5834.luckyPaws.entity.Image;
import cs5834.luckyPaws.entity.Pet;
import cs5834.luckyPaws.respository.ImageRepository;
import cs5834.luckyPaws.respository.PetRepository;
import cs5834.luckyPaws.util.ImageUtil;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@CrossOrigin(origins = { "https://luckypaws.discovery.cs.vt.edu", "http://localhost:4000", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class PetController {
    Date date = new Date();
    Timestamp timestamp2 = new Timestamp(date.getTime());
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    ImageRepository imageRepository;
    
    
    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets(){
        try {
            List<Pet> pets = new ArrayList<Pet>();

            pets.addAll(petRepository.findAll());
            //System.out.println(pets.toString());
            
            if (pets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pets, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println( e.toString() );
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/pets/type/{type}")
    public ResponseEntity<List<Pet>> getPetsByType(@PathVariable("type") String petType) {
        List<Pet> pets = petRepository.findByPetType(petType);
        
        if (!pets.isEmpty()) {
            return new ResponseEntity<>(pets, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/pets/breed/{breed}")
    public ResponseEntity<List<Pet>> getPetsByBreed(@PathVariable("breed") String breed) {
        List<Pet> pets = petRepository.findByBreed(breed);
        
        if (!pets.isEmpty()) {
            return new ResponseEntity<>(pets, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/pets/flag/{flag}")
    public ResponseEntity<List<Object>> getPetsByflag(@PathVariable("flag") String flag) {
        //List<Pet> pets = petRepository.findByFlag(flag);
        List<Object> pets = petRepository.findPetByFlag(flag);


        if (!pets.isEmpty()) {
            return new ResponseEntity<>(pets, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    //@PostMapping("/pet")
    @RequestMapping(path = "/pets", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Pet> createPet(@RequestParam("pet_name") String pet_name,
                                        @RequestParam("petType") String petType,
                                        @RequestParam("location") String location,
                                        @RequestParam("description") String description,
                                        @RequestParam("size") String size,
                                        @RequestParam("color") String color,
                                        @RequestParam("breed") String breed,
                                        @RequestParam("flag") String flag,
                                        @RequestParam("age") Integer age,
                                        @RequestPart MultipartFile imagedata,
                                         @RequestParam("user_id") long userId) {
      try {

          Image image = imageRepository.save(Image.builder()
                  .name(imagedata.getOriginalFilename())
                  .type(imagedata.getContentType())
                  .image(ImageUtil.compressImage(imagedata.getBytes())).build());
          
            Pet _pet = petRepository.save(new Pet(pet_name, 
                                                petType,
                                                location, 
                                                breed,
                                                age,
                                                flag, 
                                                description,
                                                size,
                                                color,"Created",timestamp2,timestamp2, image.getId(),0,userId,0));
            return new ResponseEntity<>(_pet, HttpStatus.CREATED);
            
      } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping("/pets/request/{id}/{updated_user_id}/{req_flag}")
    public ResponseEntity<Pet> updateReqStatus(@PathVariable("id") long id, @PathVariable("updated_user_id") long updatedUserId, @PathVariable("req_flag") String req_flag) {
        System.out.println("updatedUserId----"+updatedUserId);
        List<Pet> petData = petRepository.updatePetReqFlag(id);
        if (!petData.isEmpty()) {
            Pet _pet = petData.get(0);
            _pet.setReqFlag(req_flag);
            _pet.setUpdated_user_id(updatedUserId);
            _pet.setUpdated_date(timestamp2);
            return new ResponseEntity<>(petRepository.save(_pet), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/pets/search/{searchParam}")
    public ResponseEntity<List<Object>> searchPet(@PathVariable("searchParam") String searchParam) {
        //List<Pet> pets = petRepository.findByFlag(flag);
        List<Object> pets = petRepository.searchPet(searchParam);


        if (!pets.isEmpty()) {
            return new ResponseEntity<>(pets, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<Object> getPetById(@PathVariable("id") long id) {
        List<Object> petData = petRepository.findById(id);

        if (!petData.isEmpty()) {
            return new ResponseEntity<>(petData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pets/request/{user_id}")
    public ResponseEntity<Object> getPetByUserId(@PathVariable("user_id") long user_id) {
        List<Object> petData = petRepository.findByUserId(user_id);

        if (!petData.isEmpty()) {
            return new ResponseEntity<>(petData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
