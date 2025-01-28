package ada.chore_api_v2.Reward;

import ada.chore_api_v2.GenericResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@RestController
public class RewardController {

    private final RewardService rewardService;
    private final RewardRepository rewardRepository;

    public RewardController(RewardService rewardService, RewardRepository rewardRepository) {
        this.rewardService = rewardService;
        this.rewardRepository = rewardRepository;
    }

    // Create new Reward - front end and dev
    @PostMapping("/users/{userId}/rewards")
    public ResponseEntity<GenericResponseBody> createReward(
            @PathVariable int userId,
            @Valid @RequestBody Reward rewardRequest,
            BindingResult result) {

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            GenericResponseBody errorResponse = new GenericResponseBody("Invalid request body");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        GenericResponseBody newReward = rewardService.createReward(userId, rewardRequest);
        if (newReward == null) {
            GenericResponseBody userNotFound = new GenericResponseBody("User not found");
            return new ResponseEntity<>(userNotFound, HttpStatus.NOT_FOUND);
        }
        if (Objects.equals(newReward.getMessage(), "Reward already exists for this user")) {
            return new ResponseEntity<>(newReward, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(newReward, HttpStatus.CREATED);
    }

    // Get all rewards -dev only
    @GetMapping("/rewards")
    public Set<GenericResponseBody> getAllRewards() {
        Iterable<Reward> rewards = rewardRepository.findAll();
        Set<GenericResponseBody> rewardResponses = new HashSet<>();

        for (Reward reward : rewards) {
            rewardResponses.add(new RewardResponseBody(reward));
        }

        return rewardResponses;
    }

    // Get rewards by user ID - front end and dev
    @GetMapping("/users/{userId}/rewards")
    public ResponseEntity<Set<GenericResponseBody>> getRewardsByUserId(@PathVariable int userId) {
        Set<GenericResponseBody> rewards = rewardService.getRewardsByUserId(userId);
        if (rewards == null || rewards.isEmpty()) {
            GenericResponseBody noRewardsFound = new GenericResponseBody("No rewards found for this user");
            return new ResponseEntity<>(Set.of(noRewardsFound), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    // Get a reward by ID -dev only
    @GetMapping("/rewards/{rewardId}")
    public ResponseEntity<GenericResponseBody> getRewardById(@PathVariable int rewardId) {
        GenericResponseBody foundReward = rewardService.getRewardById(rewardId);
        if (foundReward == null) {
            GenericResponseBody rewardNotFound = new GenericResponseBody("Reward not found");
            return new ResponseEntity<>(rewardNotFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundReward, HttpStatus.OK);
    }

    // Update reward - front end  and dev
    @PatchMapping("/rewards/{rewardId}")
    public ResponseEntity<GenericResponseBody> updateReward(
            @PathVariable int rewardId,
            @Valid @RequestBody Reward rewardRequest,
            BindingResult result) {

        if (result.hasErrors()) {
            GenericResponseBody errorResponse = new GenericResponseBody("Invalid request body");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        GenericResponseBody updatedReward = rewardService.updateReward(rewardId, rewardRequest);
        if (updatedReward == null) {
            GenericResponseBody rewardNotFound = new GenericResponseBody("Reward not found or does not belong to the specified user");
            return new ResponseEntity<>(rewardNotFound, HttpStatus.NOT_FOUND);
        }
        updatedReward.setMessage("Reward updated successfully");
        return new ResponseEntity<>(updatedReward, HttpStatus.OK);
    }

    // Delete reward
    @DeleteMapping("/rewards/{rewardId}")
    public ResponseEntity<GenericResponseBody> deleteReward(@PathVariable int rewardId) {
        GenericResponseBody results = rewardService.deleteRewardById(rewardId);
        if (Objects.equals(results.getMessage(), "Reward not found")) {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}