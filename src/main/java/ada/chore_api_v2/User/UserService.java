package ada.chore_api_v2.User;

import ada.chore_api_v2.GenericResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final NewUserService newUserService;

    public UserService(UserRepository userRepository, NewUserService newUserService) {
        this.userRepository = userRepository;
        this.newUserService = newUserService;
    }

    // Create a new user
    public GenericResponseBody createUser(User user) {
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            return null;
        }
        User newUser = userRepository.saveAndFlush(user);
        Set<GenericResponseBody> defaultRewards = newUserService.addDefaultRewardsToNewUser(newUser);
        Set<GenericResponseBody> defaultChores = newUserService.addDefaultChoresToNewUser(newUser);
        UserResponseBody response = new UserResponseBody(newUser);
        response.setChores(defaultChores);
        response.setRewards(defaultRewards);
        response.setMessage("User created successfully");
        return response;
    }

    // Get all users
    public Set<GenericResponseBody> getAllUsers(){
        Iterable<User> users = userRepository.findAll();
        Set<GenericResponseBody> userResponseBodies = new HashSet<>();
        users.forEach(user -> userResponseBodies.add(new UserResponseBody(user)));
        return userResponseBodies;
    }

    // Get one user by Id
    public GenericResponseBody getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserResponseBody response = new UserResponseBody(user.get());
            response.setMessage("User found");
            return response;
        }
        return new GenericResponseBody("User not found");
//        return user.map(UserResponseBody::new).orElse(null);
    }

    //Get one user by username
    public GenericResponseBody getUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            UserResponseBody response = new UserResponseBody(user.get());
            response.setMessage("User found");
            return response;
        }
        return new GenericResponseBody("User not found");
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

    // Get all categories of one user
    public ResponseEntity<Set<String>> getUserCategories (int userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<Set<String>>(user.get().getChoreCategories(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
