package cs5834.luckyPaws.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cs5834.luckyPaws.respository.UserRepository;

@Component
@Scope("session")
public class SessionInfo {
    
    @Autowired
    private UserRepository userRepository;
    
    private User user;

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
