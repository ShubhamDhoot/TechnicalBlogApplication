package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.User;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public boolean login(User user){
    User existingUser=userRepository.checkUser(user.getUsername(),user.getPassword());
    if(existingUser!=null){
      return true;
    }
    return false;

  }

  public void registerUser(User user){
    userRepository.registerUser(user);
  }
}
