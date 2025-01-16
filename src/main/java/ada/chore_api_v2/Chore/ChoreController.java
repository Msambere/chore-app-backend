package ada.chore_api_v2.Chore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chores")
public class ChoreController {
    private final ChoreService choreService;

    public ChoreController(ChoreService choreService) {
        this.choreService = choreService;
    }

    @PostMapping()
    public ResponseEntity<Chore> addChore(@RequestBody Chore chore) {
        return new ResponseEntity<>(choreService.createChore(chore), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Chore>> getAllChores() {
        return new ResponseEntity<>(choreService.getAllChores(), HttpStatus.OK);
    }

    @GetMapping("/{choreId}")
    public ResponseEntity<Chore> getChoreById(@PathVariable Long choreId) {
        return new ResponseEntity<>(choreService.getChoreById(choreId), HttpStatus.OK);
    }

    @DeleteMapping("/{choreId}")
    public ResponseEntity<String> deleteChore(@PathVariable int choreId) {
        return new ResponseEntity<>(choreService.deleteChoreById(choreId), HttpStatus.OK);
    }
}
