package ada.chore_api_v2.Reward;

import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;
    private final UserRepository userRepository;

    public RewardService(RewardRepository rewardRepository, UserRepository userRepository) {
        this.rewardRepository = rewardRepository;
        this.userRepository = userRepository;
    }

    // Create a new reward
    public GenericResponseBody createReward(int userId, Reward rewardRequest) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            rewardRequest.setUser(foundUser.get());
            if (rewardRepository.findByUserId(userId).stream()
                    .anyMatch(reward -> reward.getName().equalsIgnoreCase(rewardRequest.getName()) &&
                            reward.getDescription().equalsIgnoreCase(rewardRequest.getDescription()) &&
                            reward.getInMission().equals(rewardRequest.getInMission()) &&
                            reward.getPointsNeeded().equals(rewardRequest.getPointsNeeded()))) {
                return new GenericResponseBody("Reward already exists for this user");
            }
            RewardResponseBody newReward = new RewardResponseBody(rewardRepository.save(rewardRequest));
            newReward.setMessage("Reward created successfully");
            return newReward;
        }
        return new GenericResponseBody("User not found");
    }

    // Get all rewards
    public Set<GenericResponseBody> getAllRewards() {
        Iterable<Reward> rewards = rewardRepository.findAll();
        Set<GenericResponseBody> rewardResponseBodies = new HashSet<>();
        rewards.forEach(reward -> rewardResponseBodies.add(new RewardResponseBody(reward)));
        return rewardResponseBodies;
    }

    // Get rewards by userId
    public Set<GenericResponseBody> getRewardsByUserId(int userId) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (foundUser.isPresent()) {
            Set<GenericResponseBody> rewardResponseBodies = new HashSet<>();
            rewardRepository.findByUserId(userId).forEach(reward -> rewardResponseBodies.add(new RewardResponseBody(reward)));
            return rewardResponseBodies;
        }
        return null;
    }

    // Get Reward by id
    public GenericResponseBody getRewardById(int rewardId) {
        Optional<Reward> reward = rewardRepository.findById(rewardId);
        if (reward.isPresent()) {
            return new RewardResponseBody(reward.get());
        }
        return null;
    }

    // Update a reward
    public GenericResponseBody updateReward(int rewardId, Reward rewardRequest) {
        Optional<Reward> reward = rewardRepository.findById(rewardId);
        if (reward.isPresent()) {
            Reward updatedReward = reward.get();
            if (rewardRequest.getName() != null) {
                updatedReward.setName(rewardRequest.getName());
            }
            if (rewardRequest.getDescription() != null) {
                updatedReward.setDescription(rewardRequest.getDescription());
            }
            if (rewardRequest.getInMission() != null) {
                updatedReward.setInMission(rewardRequest.getInMission());
            }
            if (rewardRequest.getPointsNeeded() != null) {
                updatedReward.setPointsNeeded(rewardRequest.getPointsNeeded());
            }
            if (!rewardRepository.findByNameAndDescriptionAndInMissionAndPointsNeeded(updatedReward.getName(), updatedReward.getDescription(), updatedReward.getInMission(), updatedReward.getPointsNeeded()).isEmpty()){
                return new GenericResponseBody("Reward already exists for this user");
            }
            RewardResponseBody updatedRewardResponse = new RewardResponseBody( rewardRepository.save(updatedReward));
            updatedRewardResponse.setMessage("Reward updated successfully");
            return updatedRewardResponse;
        }
        return null;
    }

    // Delete a reward
    public GenericResponseBody deleteRewardById(int rewardId) {
        Optional<Reward> reward = rewardRepository.findById(rewardId);
        if (reward.isPresent()) {
            rewardRepository.delete(reward.get());
            return new GenericResponseBody("Reward deleted successfully");
        }
        return new GenericResponseBody("Reward not found");
    }
}