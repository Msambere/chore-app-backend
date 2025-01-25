package ada.chore_api_v2.Mission;


import ada.chore_api_v2.GenericResponseBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@RestController
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    // Create new Mission
    @PostMapping("/users/{userId}/missions")
    public ResponseEntity<GenericResponseBody> addMission(@PathVariable int userId, @Valid @RequestBody Mission missionRequest, BindingResult result) {
        if (result.hasErrors()) {
            GenericResponseBody errorResponse = new GenericResponseBody("Invalid request body");
            return new ResponseEntity<GenericResponseBody>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        GenericResponseBody newMission = missionService.createMission(userId, missionRequest);
        if (newMission == null) {
            GenericResponseBody userNotFound = new GenericResponseBody("User not found");
            return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
        }
        if (newMission.getMessage() != null) {
            return new ResponseEntity<>(newMission, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<GenericResponseBody>(newMission, HttpStatus.CREATED);
    }


    // Get all Missions - dev only
    @GetMapping("/missions")
    public ResponseEntity<Set<GenericResponseBody>> getAllMissions() {
        return new ResponseEntity<>(missionService.getAllMissions(), HttpStatus.OK);
    }

    // Get a mission by id
    @GetMapping("/missions/{missionId}")
    public ResponseEntity<GenericResponseBody> getMissionById(@PathVariable int missionId) {
        GenericResponseBody foundMission = missionService.getMissionById(missionId);
        if (foundMission == null) {
            GenericResponseBody missionNotFound = new GenericResponseBody("Mission not found");
            return new ResponseEntity<>(missionNotFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundMission, HttpStatus.OK);
    }



    // Update Mission:
    @PatchMapping("/missions/{missionId}")
    public ResponseEntity<GenericResponseBody> updateMission(@PathVariable int missionId, @Valid @RequestBody Mission missionRequest) {
        MissionResponseBody updateMission = missionService.updateMission(missionId, missionRequest);
        if (updateMission == null) {
            GenericResponseBody missionNotFound = new GenericResponseBody("Mission not found");
            return new ResponseEntity<>(missionNotFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GenericResponseBody>(updateMission, HttpStatus.OK);
    }

    //delete a mission by ID
    @DeleteMapping("/missions/{missionId}")
    public ResponseEntity<GenericResponseBody> deleteMission(@PathVariable int missionId) {
        GenericResponseBody results = missionService.deleteMissionById(missionId);
        if (Objects.equals(results.getMessage(), "Mission not found")) {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}