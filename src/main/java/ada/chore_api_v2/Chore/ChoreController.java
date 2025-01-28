package ada.chore_api_v2.Chore;

import ada.chore_api_v2.GenericResponseBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.Objects;
import java.util.Set;

@CrossOrigin
@RestController
public class ChoreController {
    private final ChoreService choreService;

    public ChoreController(ChoreService choreService, View error) {
        this.choreService = choreService;
    }
    // Create new Chore
    @PostMapping("/users/{userId}/chores")
    public ResponseEntity<GenericResponseBody> createChore(@PathVariable int userId, @Valid @RequestBody Chore choreRequest, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            GenericResponseBody errorResponse = new GenericResponseBody("Invalid request body");
            return new ResponseEntity<GenericResponseBody>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        GenericResponseBody newChore = choreService.createChore(userId,choreRequest);
        if(newChore == null) {
            GenericResponseBody userNotFound = new GenericResponseBody("User not found");
            return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
        }
        if(Objects.equals(newChore.getMessage(), "Chore already exists")) {
            return new ResponseEntity<>(newChore, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<GenericResponseBody>(newChore, HttpStatus.CREATED);
    }

    //Get all chores - for dev only
    @GetMapping("/chores")
    public ResponseEntity<Set<GenericResponseBody>> getAllChores() {
        return new ResponseEntity<>(choreService.getAllChores(), HttpStatus.OK);
    }

    // Get a chore by id - for dev only
    @GetMapping("/chores/{choreId}")
    public ResponseEntity<GenericResponseBody> getChoreById(@PathVariable Integer choreId) {
        GenericResponseBody foundChore = choreService.getChoreById(choreId);
        if(foundChore == null) {
            GenericResponseBody choreNotFound = new GenericResponseBody("Chore not found");
            return new ResponseEntity<>(choreNotFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundChore, HttpStatus.OK);
    }

    // Update chore
    @PatchMapping("chores/{choreId}")
    public ResponseEntity<GenericResponseBody> updateChore(@PathVariable Integer choreId, @RequestBody Chore choreRequest, BindingResult result) {
        if(result.hasErrors()) {
            GenericResponseBody errorResponse = new GenericResponseBody("Invalid request body");
            return new ResponseEntity<GenericResponseBody>(errorResponse, HttpStatus.BAD_REQUEST);
        }
       GenericResponseBody updatedChore = choreService.updateChore( choreId, choreRequest);
        if(updatedChore == null) {
            GenericResponseBody choreNotFound = new GenericResponseBody("Chore not found");
            return new ResponseEntity<>(choreNotFound, HttpStatus.NOT_FOUND);
        }
        updatedChore.setMessage("Chore updated successfully");
        return new ResponseEntity<GenericResponseBody>(updatedChore, HttpStatus.OK);

    }
    // Delete chore
    @DeleteMapping("/chores/{choreId}")
    public ResponseEntity<GenericResponseBody> deleteChore(@PathVariable int choreId) {
        GenericResponseBody results = choreService.deleteChoreById(choreId);
        if (Objects.equals(results.getMessage(), "Chore not found")) {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
