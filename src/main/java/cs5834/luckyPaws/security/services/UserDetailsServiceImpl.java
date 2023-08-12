package cs5834.luckyPaws.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs5834.luckyPaws.entity.User;
import cs5834.luckyPaws.respository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    List<User> users = userRepository.findByUserName(username);
    if(users.size() == 0) {
        throw new UsernameNotFoundException("User Not Found with username: " + username);
    }

    return UserDetailsImpl.build(users.get(0));
  }

}
