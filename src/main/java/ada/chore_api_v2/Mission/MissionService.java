package ada.chore_api_v2.Mission;

import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.HashSet;
import java.util.Set;


@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public MissionService(MissionRepository missionRepository, UserRepository userRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
    }

    // Create a Mission
    public MissionResponseBody createMission(int userId, Mission missionRequest) {
        Optional<User> foundUser = userRepository.findById(userId);
        System.out.println(foundUser.isPresent());
        if (foundUser.isPresent()) {
            missionRequest.setUser(foundUser.get());
            return new MissionResponseBody(missionRepository.save(missionRequest));
        }
        return null;
    }

    // Get all Missions
    public Set<GenericResponseBody> getAllMissions() {
        Iterable<Mission> missions = missionRepository.findAll();
        Set<GenericResponseBody> missionResponseBodies = new HashSet<>();
        missions.forEach(mission -> missionResponseBodies.add(new MissionResponseBody(mission)));

        return missionResponseBodies;
    }


    // Get a Mission by ID
    public GenericResponseBody getMissionById(int id) {
        Optional<Mission> mission = missionRepository.findById(id);
        return mission.map(MissionResponseBody::new).orElse(null);
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
        MissionResponseBody responseBody = new MissionResponseBody(updatedMission);
        responseBody.setMessage("Successfully updated Mission");
        return responseBody;
    }

    // Delete a Mission by ID
    public GenericResponseBody deleteMissionById(int id) {
        Optional<Mission> mission = missionRepository.findById((int) id);
        if (mission.isPresent()) {
            missionRepository.delete(mission.get());
            return new GenericResponseBody("Mission deleted successfully");
        }
        return new GenericResponseBody("Mission not found");
    }
}