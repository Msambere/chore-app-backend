package ada.chore_api_v2.Chore;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/chores")
public class ChoreController {
    private final ChoreService choreService;

    public ChoreController(ChoreService choreService) {
        this.choreService = choreService;
    }

    @PostMapping("/users/{userId}/chores")
    public ResponseEntity<ChoreResponseBody> addChore(@PathVariable int userId, @Valid @RequestBody Chore choreRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ChoreResponseBody newChore = choreService.createChore(userId,choreRequest);
        if(newChore == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ChoreResponseBody>(newChore, HttpStatus.CREATED);
    }

    @GetMapping("/chores")
    public ResponseEntity<Iterable<Chore>> getAllChores() {
        return new ResponseEntity<>(choreService.getAllChores(), HttpStatus.OK);
    }

    @GetMapping("/chores/{choreId}")
    public ResponseEntity<Chore> getChoreById(@PathVariable Long choreId) {
        return new ResponseEntity<>(choreService.getChoreById(choreId), HttpStatus.OK);
    }

    @DeleteMapping("/chores/{choreId}")
    public ResponseEntity<String> deleteChore(@PathVariable int choreId) {
        return new ResponseEntity<>(choreService.deleteChoreById(choreId), HttpStatus.OK);
    }
}
