package ada.chore_api_v2.User;

import ada.chore_api_v2.GenericResponseBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create new user
    @PostMapping()
    public ResponseEntity<GenericResponseBody> createUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            GenericResponseBody errorResponse = new GenericResponseBody("Invalid request body");
            return new ResponseEntity<GenericResponseBody>(errorResponse, HttpStatus.BAD_REQUEST); //Figure out how to add message or make custom response body
        }
        GenericResponseBody newUser = userService.createUser(user);
        if (newUser == null) {
            GenericResponseBody userAlreadyExists = new GenericResponseBody("User already exists");
            return new ResponseEntity<GenericResponseBody>(userAlreadyExists, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<GenericResponseBody>(newUser, HttpStatus.CREATED);
    }

    // Get all users - Dev Only
    @GetMapping()
    public ResponseEntity<Set<GenericResponseBody>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // Get one user
    @GetMapping("/{userId}")
    public ResponseEntity<GenericResponseBody> getUserById(@PathVariable int userId) {
        GenericResponseBody newUser = userService.getUserById(userId);
        if (newUser == null) {
            GenericResponseBody userNotFound = new GenericResponseBody("User not found");
            return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GenericResponseBody>(userService.getUserById(userId), HttpStatus.OK);
    }

    //Get chores of one user
    @GetMapping("/{userId}/chores")
    public ResponseEntity<Set<GenericResponseBody>> getChoresByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getChoresByUserId(userId), HttpStatus.OK);
    }

    //Get missions of one user
    @GetMapping("/{userId}/missions")
    public ResponseEntity<Set<GenericResponseBody>> getMissionsByUserId(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getMissionsByUserId(userId), HttpStatus.OK);
    }

    // Get all categories of one user
    @GetMapping("/{userId}/categories")
    public ResponseEntity<Set<String>> getCategoriesByUserId(@PathVariable int userId) {
        return userService.getUserCategories(userId);

    }

    // Update User
    @PatchMapping("/{userId}")
    public ResponseEntity<GenericResponseBody> updateUser(@PathVariable int userId, @RequestBody User userRequest, BindingResult result) {
        if(result.hasErrors()) {
            GenericResponseBody errorResponse = new GenericResponseBody("Invalid request body");
            return new ResponseEntity<GenericResponseBody>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        GenericResponseBody updatedUser = userService.updateUser(userRequest, userId);
        if (updatedUser == null) {
            GenericResponseBody userNotFound = new GenericResponseBody("User not found");
            return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GenericResponseBody>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<GenericResponseBody> deleteUserById(@PathVariable int userId) {
        GenericResponseBody results = userService.deleteUserById(userId);
        if (Objects.equals(results.getMessage(), "User not found")) {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
