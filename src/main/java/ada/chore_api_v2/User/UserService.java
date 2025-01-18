package ada.chore_api_v2.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseBody createUser(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null) {
            return null;
        }
        userRepository.save(user);
        UserResponseBody response = new UserResponseBody(userRepository.save(user));
        return response;
    }


    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            return foundUser;
        }
        return null;
    }

    public String deleteUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User deleted";
        }
        return "User not found";
    }
}
