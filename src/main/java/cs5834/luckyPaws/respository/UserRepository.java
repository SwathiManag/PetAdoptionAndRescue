package cs5834.luckyPaws.respository;

import cs5834.luckyPaws.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findById(long user_id);

    @Query("SELECT u FROM User u WHERE u.user_id = ?1")
    List<Object> findUserById(long user_id);


    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    List<User> findByUserName(String userName);
    
    @Query("SELECT u FROM User u WHERE u.emailId = ?1")
    public User findByEmail(String email);
    
    
    Boolean existsByUserName(String userName);

    Boolean existsByEmailId(String emailId);

    @Query("SELECT u FROM User u WHERE u.userName = ?1 and u.securityQues = ?2")
    List<User>  validateUser(String userName,String securityQues);
    
}
