package ada.chore_api_v2.Mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    // Create or update a mission
    @PostMapping
    public ResponseEntity<MissionResponseBody> createMission(@RequestBody Mission mission) {
        MissionResponseBody savedMission = missionService.saveMission(mission);
        return new ResponseEntity<>(savedMission, HttpStatus.CREATED);
    }

    // Get all missions with pagination
    @GetMapping
    public ResponseEntity<Page<MissionResponseBody>> getAllMissionsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MissionResponseBody> missions = missionService.getAllMissionsWithPagination(page, size);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Get a mission by ID
    @GetMapping("/{id}")
    public ResponseEntity<MissionResponseBody> getMissionById(@PathVariable int id) {
        Optional<MissionResponseBody> mission = missionService.getMissionById(id);
        return mission.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get missions by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MissionResponseBody>> getMissionsByUserId(@PathVariable int userId) {
        List<MissionResponseBody> missions = missionService.getMissionsByUserId(userId);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Get missions by user ID with pagination
    @GetMapping("/user/{userId}/paginated")
    public ResponseEntity<Page<MissionResponseBody>> getMissionsByUserIdWithPagination(
            @PathVariable int userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MissionResponseBody> missions = missionService.getMissionsByUserIdWithPagination(userId, page, size);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Get missions by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MissionResponseBody>> getMissionsByCategory(@PathVariable String category) {
        List<MissionResponseBody> missions = missionService.getMissionsByCategory(category);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Delete a mission by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMissionById(@PathVariable int id) {
        missionService.deleteMissionById(id);
        return new ResponseEntity<>("Mission deleted successfully", HttpStatus.NO_CONTENT);
    }
}