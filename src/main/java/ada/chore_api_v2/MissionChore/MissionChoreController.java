package ada.chore_api_v2.MissionChore;

import ada.chore_api_v2.Chore.ChoreRepository;
import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.Mission.MissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/missionchores")
public class MissionChoreController {

    private final MissionChoreService missionChoreService;


    public MissionChoreController(MissionChoreService missionChoreService, ChoreRepository choreRepository, MissionRepository missionRepository) {
        this.missionChoreService = missionChoreService;
    }

    // Create new ChoreMission - Dev Only
    @PostMapping
    public ResponseEntity<GenericResponseBody> createMissionChore(
            @RequestParam Integer choreId,
            @RequestParam Integer missionId) {
        // Create new MissionCore
        return missionChoreService.createMissionChore(missionId, choreId);

    }

    // Get all ChoreMission - Dev Only
    @GetMapping
    public ResponseEntity<Set<GenericResponseBody>> getAllMissionChores() {
        return new ResponseEntity<>(missionChoreService.getAllMissionChores(), HttpStatus.OK);
    }

    // Update ChoreMission
    @PatchMapping
    public ResponseEntity<GenericResponseBody> updateMissionChore(
            @RequestParam Integer missionId,
            @RequestParam Integer choreId
    ) {

        GenericResponseBody updatedMissionChore = missionChoreService.updateMissionChore(missionId, choreId);

        if (updatedMissionChore == null) {
            return new ResponseEntity<>(new GenericResponseBody("missionChore not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedMissionChore, HttpStatus.OK);
    }
}
