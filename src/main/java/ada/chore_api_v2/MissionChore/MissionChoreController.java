package ada.chore_api_v2.MissionChore;

import ada.chore_api_v2.Mission.Mission;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missionchore")
public class MissionChoreController {

    private final MissionChoreService missionChoreService;

    public MissionChoreController(MissionChoreService missionChoreService) {
        this.missionChoreService = missionChoreService;
    }

    // Create new ChoreMission
    @PostMapping
    public ResponseEntity<MissionChore> createMissionChore(
            @RequestParam Integer choreId,
            @RequestParam Integer missionId,
            @RequestParam Boolean isCompleted) {

        MissionChore newMissionChore = missionChoreService.createMissionChore(missionId, choreId, isCompleted);
        if (newMissionChore == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newMissionChore, HttpStatus.CREATED);
    }

    // Get all ChoreMission
    @GetMapping
    public ResponseEntity<Iterable<MissionChore>> getAllMissionChore() {
        Iterable<MissionChore> allMissionChore = missionChoreService.getAllMissionChore() ;
        return new ResponseEntity<>(allMissionChore, HttpStatus.OK);
    }

    // Update ChoreMission
    @PatchMapping
    public ResponseEntity<MissionChore> updateMissionChore(
            @RequestParam Integer missionId,
            @RequestParam Integer choreId,
            @RequestBody Boolean isCompleted) {

        MissionChoreId missionChoreId = new MissionChoreId(missionId, choreId);
        MissionChore updatedMissionChore = missionChoreService.updateMissionChore(missionChoreId, isCompleted);

        if (updatedMissionChore == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMissionChore, HttpStatus.OK);
    }
}
