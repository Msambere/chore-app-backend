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
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission savedMission = missionService.saveMission(mission);
        return new ResponseEntity<>(savedMission, HttpStatus.CREATED);
    }

    // Get all missions with pagination
    @GetMapping
    public ResponseEntity<Page<Mission>> getAllMissionsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Mission> missions = missionService.getAllMissionsWithPagination(page, size);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Get a mission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMissionById(@PathVariable int id) {
        Optional<Mission> mission = missionService.getMissionById(id);
        return mission.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get missions by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Mission>> getMissionsByUserId(@PathVariable int userId) {
        List<Mission> missions = missionService.getMissionsByUserId(userId);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Get missions by user ID with pagination
    @GetMapping("/user/{userId}/paginated")
    public ResponseEntity<List<Mission>> getMissionsByUserIdWithPagination(
            @PathVariable int userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Mission> missions = missionService.getMissionsByUserIdWithPagination(userId, page, size);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Get missions by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Mission>> getMissionsByCategory(@PathVariable String category) {
        List<Mission> missions = missionService.getMissionsByCategory(category);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Get missions started after a specific date
    @GetMapping("/started-after")
    public ResponseEntity<List<Mission>> getMissionsStartedAfter(@RequestParam("date") String date) {
        LocalDateTime dateStarted = LocalDateTime.parse(date);
        List<Mission> missions = missionService.getMissionsStartedAfter(dateStarted);
        return new ResponseEntity<>(missions, HttpStatus.OK);
    }

    // Delete a mission by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMissionById(@PathVariable int id) {
        missionService.deleteMissionById(id);
        return new ResponseEntity<>("Mission deleted successfully", HttpStatus.NO_CONTENT);
    }
}