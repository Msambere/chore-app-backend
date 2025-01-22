package ada.chore_api_v2.Mission;

import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public MissionService(MissionRepository missionRepository, UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
    }

    // Get all Missions
    public Iterable<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    //Update a Mission
    public MissionResponseBody updateMission(int missionId, Mission missionRequest) {
        Optional<Mission> optionalMission = missionRepository.findById(missionId);
        if (!optionalMission.isPresent()) {
            return null;
        }
        Mission mission = optionalMission.get();
        if (missionRequest.getTimeElapsed() != null) {
            mission.setTimeElapsed(missionRequest.getTimeElapsed());
        }
        if (missionRequest.getTotalUnredeemedPoints() != null) {
            mission.setTotalUnredeemedPoints(missionRequest.getTotalUnredeemedPoints());
        }
        Mission updatedMission = missionRepository.save(mission);
        return new MissionResponseBody(updatedMission);
    }

    // Create a Mission
    public MissionResponseBody createMission(int userId, Mission missionRequest) {
        Optional<User> foundUser = userRepository.findById(userId);
        if (!foundUser.isPresent()) {
            return null;
        }
        User user = foundUser.get();
        missionRequest.setUser(user);
        missionRequest.setDateStarted(LocalDateTime.now());
        missionRequest.setTotalUnredeemedPoints(0);
        missionRequest.setTimeElapsed(0L);

        Mission savedMission = missionRepository.save(missionRequest);
        return new MissionResponseBody(savedMission);
    }

    // Get a Mission by ID
    public MissionResponseBody getMissionById(int id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isPresent()) {
            return new MissionResponseBody(mission.get());
        }
        return null;
    }

    // Delete a Mission by ID
    public String deleteMissionById(int id) {
        Optional<Mission> mission = missionRepository.findById((int) id);
        if (mission.isPresent()) {
            missionRepository.delete(mission.get());
            return "Mission deleted successfully";
        }
        return "Mission not found";
    }
}