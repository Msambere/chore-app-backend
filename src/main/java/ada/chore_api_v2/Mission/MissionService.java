package ada.chore_api_v2.Mission;

import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public MissionService(MissionRepository missionRepository, UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
    }

    // Get all Missions
    public Iterable<MissionResponseBody> getAllMissions() {
        Iterable<Mission> missions = missionRepository.findAll();
        Set<MissionResponseBody> missionResponseBodies = new HashSet<>();
        missions.forEach(mission -> missionResponseBodies.add(new MissionResponseBody(mission)));

        return missionResponseBodies;
    }

    // Create a Mission
    public MissionResponseBody createMission(int userId, Mission missionRequest) {
        Optional<User> foundUser = userRepository.findById(userId);
        System.out.println(foundUser.isPresent());
        if (foundUser.isPresent()) {
            missionRequest.setUser(foundUser.get());
//            missionRequest.setDateStarted(LocalDateTime.now());
//            missionRequest.setTimeElapsed(Duration.ofMinutes(0L));
//            missionRequest.setTotalUnredeemedPoints(0);
            return new MissionResponseBody(missionRepository.save(missionRequest));
        }
        return null;
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