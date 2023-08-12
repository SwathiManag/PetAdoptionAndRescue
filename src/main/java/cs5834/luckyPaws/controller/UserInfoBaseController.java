package cs5834.luckyPaws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import cs5834.luckyPaws.entity.User;
import cs5834.luckyPaws.respository.UserRepository;

public class UserInfoBaseController {
    
    
    @Autowired
    protected UserRepository userRepository;
    
    protected User user;

    public User getCurrentUser() {
        
        if (user == null) {
            String userName = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            
            if(userName != null) {
                List<User> users = userRepository.findByUserName(userName); 
                
                user = users.get(0);
            }
        }
        
        return user;
    }
    

}
