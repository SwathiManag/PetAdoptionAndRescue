package cs5834.luckyPaws.controller;
import cs5834.luckyPaws.entity.User;
import cs5834.luckyPaws.payload.response.MessageResponse;
import cs5834.luckyPaws.respository.PetRepository;
import cs5834.luckyPaws.respository.UserRepository;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "https://luckypaws.discovery.cs.vt.edu", "http://localhost:4000", "http://localhost:8081" })
@RestController
@RequestMapping("/api")
public class UserController {
    Date date = new Date();
    Timestamp timestamp2 = new Timestamp(date.getTime());
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users")
    public ResponseEntity<List<User>> fetchUsers() {
        try {
            List<User> user = new ArrayList<User>();

            user.addAll(userRepository.findAll());
            System.out.println(user.toString());
            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") long id) {
        List<Object> userData = userRepository.findUserById(id);

        if (!userData.isEmpty()) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        try {
            System.out.println("User Name : " + user.getUserName());
            System.out.println("Pwd : " + user.getPassword());
            System.out.println("email : " + user.getEmailId());

            User _user = userRepository
                    .save(new User(user.getFirstName(), user.getLastName(), user.getEmailId(),
                            user.getUserName(), encrpytPassword(user.getPassword()), user.getPhoneNumber(),
                            user.getAddress(), "user" ,timestamp2 , timestamp2, user.getSecurityQues()));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        List<User> userData = userRepository.findById(id);
        if (!userData.isEmpty()) {
            User _user = userData.get(0);
            _user.setFirstName(user.getFirstName());
            _user.setLastName(user.getLastName());
//            _user.setEmailId(user.getEmailId());
//            _user.setUserName(user.getUserName());
//            _user.setPassword(encrpytPassword(user.getPassword()));
            _user.setPhoneNumber(user.getPhoneNumber());
            _user.setAddress(user.getAddress());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            List<Long> petIdList= petRepository.getPetIdByUserId(id);
            if(!petIdList.isEmpty()) {
                petRepository.deleteAllById(petIdList);
            }
            userRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String encrpytPassword(String password) {
        //Logic for password encryption
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("javatechie"); // encryptor's private key
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        String encryptedPassword = encryptor.encrypt(password);
        System.out.println("Password : " + password);

        System.out.println("encryptedPassword : " + encryptedPassword);
        return encryptedPassword;
    }

    public String decryptPassword(String password) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("javatechie"); // encryptor's private key
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        String encryptedPassword = encryptor.encrypt(password);
        System.out.println("encryptedPassword : " + encryptedPassword);
        return encryptedPassword;
    }

    @GetMapping("/user/validate/{userName}/{securityQues}")
    public ResponseEntity<List<User>> validateUser(@PathVariable("userName") String userName,
                                               @PathVariable("securityQues") String securityQues) {
        List<User> userData = userRepository.validateUser(userName, securityQues);
        if (!userData.isEmpty()){
            return new ResponseEntity<>(userData,HttpStatus.OK);        }
         else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/user/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody User user) {

        try {

            List<User> userData = userRepository.findByUserName(user.getUserName());
            if (!userData.isEmpty()) {
                User _user = userData.get(0);
                System.out.println(user.getPassword());
                _user.setPassword(encoder.encode(user.getPassword()));
                _user.setUpdatedDate(timestamp2);

                return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}

