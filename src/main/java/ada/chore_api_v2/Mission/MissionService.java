package ada.chore_api_v2.Mission;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.MissionChore.MissionChore;
import ada.chore_api_v2.MissionChore.MissionChoreRepository;
import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.stereotype.Service;
import ada.chore_api_v2.MissionChore.MissionChoreResponseBody;

import java.util.*;


@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final ChoreRepository choreRepository;
    private final MissionChoreRepository missionChoreRepository;

    public MissionService(MissionRepository missionRepository, UserRepository userRepository, ChoreRepository choreRepository, MissionChoreRepository missionChoreRepository) {
        this.missionRepository = missionRepository;
        this.userRepository = userRepository;
        this.choreRepository = choreRepository;
        this.missionChoreRepository = missionChoreRepository;
    }

    // Create a Mission - Dev Only
    public GenericResponseBody createMission(int userId, Mission missionRequest) {
        Optional<User> foundUser = userRepository.findById(userId);
        // Make a mission
        if (foundUser.isPresent()) {
            missionRequest.setUser(foundUser.get());
            Mission newMission = missionRepository.save(missionRequest);
           // Find Matching chores
            Set<Chore> choreSet = getMatchingChores(newMission.getRecurrence(), newMission.getCategory(), newMission.getTimeLimit(), newMission.getUser());
            if(choreSet.isEmpty()){
                return new GenericResponseBody("No matching chores found");
            }
            // create set of missionChoreResponseBodies
            Set<GenericResponseBody> missionChoreResponses = new HashSet<>();
            for (Chore chore : choreSet) {
                MissionChore newMissionChore = new MissionChore(newMission,chore);
                missionChoreRepository.save(newMissionChore);
                missionChoreResponses.add(new MissionChoreResponseBody(newMissionChore));
            }
            // Create missionResponseBody
            MissionResponseBody newMissionResponse = new MissionResponseBody(newMission);
            newMissionResponse.setMissionChores(missionChoreResponses);
            return newMissionResponse;
        }
        return null;
    }

    // Get all Missions - Dev Only
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

    // Helper function: Create chore list for mission
    public Set<Chore> getMatchingChores(String recurrence, String category, Long timeLimit, User user) {
        Set<Chore> chores = new HashSet<>();
        ArrayList<Chore> choreOptions = null;
        // Filter chores by recurrence and category values
        if (recurrence != null && category != null) {
            choreOptions = choreRepository.findByRecurrenceAndCategoryAndUser(recurrence, category, user);
        }
        else if(recurrence == null && category != null) {
            choreOptions = choreRepository.findByCategoryAndUser(category, user);
        }
        else if (recurrence != null) {
            choreOptions = choreRepository.findByRecurrenceAndUser(recurrence, user);
        } else {
            choreOptions = choreRepository.findByUser(user);
        }
        // Sort List from oldest to newest
        Collections.sort(choreOptions, Comparator.comparing(Chore::getLastCompletedDate));

        // Add sorted chores to set without exceeding timeLimit
        if (timeLimit == null) {
            timeLimit = Long.MAX_VALUE;
        }
        long currentLengthTime = 0L;
        for(Chore chore : choreOptions) {
            if (chore.getDuration() + currentLengthTime <= timeLimit) {
                chores.add(chore);
                currentLengthTime += chore.getDuration();
                if (currentLengthTime == timeLimit){
                    break;
                }
            }
        }
        return chores;
    }
}