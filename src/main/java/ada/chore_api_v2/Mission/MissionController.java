package ada.chore_api_v2.Mission;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping("/users/{userId}/missions")
    public ResponseEntity<MissionResponseBody> addMission(@PathVariable int userId, @Valid @RequestBody Mission missionRequest, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MissionResponseBody newMission = missionService.createMission(userId, missionRequest);
        if (newMission == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<MissionResponseBody>(newMission, HttpStatus.CREATED);
    }

    // Get all Missions
    @GetMapping("/missions")
    public ResponseEntity<Iterable<Mission>> getAllMissions() {
        return new ResponseEntity<>(missionService.getAllMissions(), HttpStatus.OK);
    }

    // get a mission by id
    @GetMapping("/missions/{missionId}")
    public ResponseEntity<MissionResponseBody> getMissionById(@PathVariable int missionId) {
        return new ResponseEntity<MissionResponseBody>(missionService.getMissionById(missionId), HttpStatus.OK);
    }

    //delete a mission by ID
    @DeleteMapping("/missions/{missionId}")
    public ResponseEntity<String> deleteMission(@PathVariable int missionId) {
        return new ResponseEntity<>(missionService.deleteMissionById(missionId), HttpStatus.OK);
    }

}