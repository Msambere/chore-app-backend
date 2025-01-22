package ada.chore_api_v2.ChoreMission;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/choremissions")
public class ChoreMissionController {

    private final ChoreMissionService choreMissionService;

    public ChoreMissionController(ChoreMissionService choreMissionService) {
        this.choreMissionService = choreMissionService;
    }

    // Create new ChoreMission
    @PostMapping
    public ResponseEntity<ChoreMission> createChoreMission(
            @RequestParam Integer choreId,
            @RequestParam Integer missionId,
            @RequestParam Boolean isCompleted) {

        ChoreMission newChoreMission = choreMissionService.createChoreMission(choreId, missionId, isCompleted);
        if (newChoreMission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newChoreMission, HttpStatus.CREATED);
    }

    // Get all ChoreMission
    @GetMapping
    public ResponseEntity<Iterable<ChoreMission>> getAllChoreMissions() {
        Iterable<ChoreMission> allChoreMissions = choreMissionService.getAllChoreMission();
        return new ResponseEntity<>(allChoreMissions, HttpStatus.OK);
    }

    // Update ChoreMission
    @PatchMapping("/{choreMissionId}")
    public ResponseEntity<ChoreMission> updateChoreMissionStatus(
            @PathVariable Integer choreMissionId,
            @RequestParam Boolean isCompleted) {

        ChoreMission updatedChoreMission = choreMissionService.updateChoreMission(choreMissionId, isCompleted);

        if (updatedChoreMission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedChoreMission, HttpStatus.OK);
    }
}
