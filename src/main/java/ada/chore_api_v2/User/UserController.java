package ada.chore_api_v2.User;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseBody> createUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //Figure out how to add message or make custom response body
        }
        UserResponseBody newUser = userService.createUser(user);
        if (newUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserResponseBody>(newUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseBody> updateUser(@PathVariable int userId, @RequestBody User userRequest, BindingResult result) {
        if(result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //Figure out how to add message or make custom response body
        }
        UserResponseBody updatedUser = userService.updateUser(userRequest, userId);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<UserResponseBody>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable int userId) {
        return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);
    }
}
