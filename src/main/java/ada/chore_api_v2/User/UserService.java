package ada.chore_api_v2.User;

import ada.chore_api_v2.Chore.ChoreResponseBody;
import ada.chore_api_v2.GenericResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public GenericResponseBody createUser(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null) {
            return null;
        }
        userRepository.save(user);
        UserResponseBody response = new UserResponseBody(userRepository.save(user));
        return response;
    }

    // Get all users
    public Set<GenericResponseBody> getAllUsers(){
        Iterable<User> users = userRepository.findAll();
        Set<GenericResponseBody> userResponseBodies = new HashSet<>();
        users.forEach(user -> userResponseBodies.add(new UserResponseBody(user)));
        return userResponseBodies;
    }

    // Get one user
    public GenericResponseBody getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        return user.map(UserResponseBody::new).orElse(null);
    }

    //Get chores of one user
    public Set<GenericResponseBody> getChoresByUserId(int userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getChoreResponses();
        }
        return null;
    }

    //Get missions of one user
    public Set<GenericResponseBody> getMissionsByUserId(int userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getMissionResponses();
        }
        return null;
    }

    // Update a user
    public GenericResponseBody updateUser(User userRequest, int userId) {
        Optional<User> foundUser =userRepository.findById(userId);
        if(foundUser.isPresent()) {
            User updatedUser = foundUser.get();
            if (userRequest.getUsername() != null) {
                updatedUser.setUsername(userRequest.getUsername());
            }
            if (userRequest.getFirstName() != null) {
                updatedUser.setFirstName(userRequest.getFirstName());
            }
            if (userRequest.getLastName() != null){
                updatedUser.setLastName(userRequest.getLastName());
            }
            if (userRequest.getEmail() != null){
                updatedUser.setEmail(userRequest.getEmail());
            }
            return new UserResponseBody(userRepository.save(updatedUser));
        }
        return null;
    }

    public GenericResponseBody deleteUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new GenericResponseBody("User deleted successfully");
        }
        return new GenericResponseBody("User not found");
    }
}
