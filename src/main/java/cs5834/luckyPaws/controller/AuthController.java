package cs5834.luckyPaws.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs5834.luckyPaws.entity.*;
import cs5834.luckyPaws.payload.request.LoginRequest;
import cs5834.luckyPaws.payload.request.SignupRequest;
import cs5834.luckyPaws.payload.response.JwtResponse;
import cs5834.luckyPaws.payload.response.MessageResponse;
import cs5834.luckyPaws.respository.UserRepository;
import cs5834.luckyPaws.security.jwt.JwtUtils;
import cs5834.luckyPaws.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
      
        try {
            if (loginRequest != null) {
                System.out.println("current user name: " + loginRequest.getUsername() +
                        ", password: " + loginRequest.getPassword());
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println("current user role: " +userDetails.getRole());
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getRole()));
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Bad Credentials!"));
        }
        
  }
  
  
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
      
      try {
          if (userRepository.existsByUserName(user.getUserName())) {
              return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: Username is already taken!"));
            }
        
            if (userRepository.existsByEmailId(user.getEmailId())) {
              return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: Email is already in use!"));
            }
            Date date = new Date();
            Timestamp timestamp2 = new Timestamp(date.getTime());

            
            User _saveUser = new User(user.getFirstName(), user.getLastName(), user.getEmailId(),
                    user.getUserName(), encoder.encode(user.getPassword()), user.getPhoneNumber(),
                    user.getAddress(), "user", timestamp2, timestamp2, user.getSecurityQues());
            
            User _user = userRepository.save( _saveUser );
            
            System.out.println("registered user sucessfully!!!!!!!!!!!!!!!!!!!!! ");
            
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
   
      } catch (Exception e) {
          System.out.println(e.toString());
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
      

   }

}
