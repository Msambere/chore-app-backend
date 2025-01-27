package ada.chore_api_v2.MissionChore;

import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.Mission.Mission;
import ada.chore_api_v2.Mission.MissionRepository;
import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MissionChoreService {

    private final MissionChoreRepository missionChoreRepository;
    private final ChoreRepository choreRepository;
    private final MissionRepository missionRepository;


    public MissionChoreService(MissionChoreRepository missionChoreRepository, ChoreRepository choreRepository, MissionRepository missionRepository) {
        this.missionChoreRepository = missionChoreRepository;
        this.choreRepository = choreRepository;
        this.missionRepository = missionRepository;
    }

    //create new MissionChore - Dev only
    public ResponseEntity<GenericResponseBody> createMissionChore( Integer missionId, Integer choreId) {
        // mission Id validation
        Optional<Mission> missionOptional = missionRepository.findById(missionId);
        if (!missionOptional.isPresent()) {
            GenericResponseBody missionNotFound = new GenericResponseBody("Mission not found");
            return new ResponseEntity<>(missionNotFound, HttpStatus.NOT_FOUND);
        }
        // Chore Id validation
        Optional<Chore> choreOptional = choreRepository.findById(choreId);
        if (!choreOptional.isPresent()) {
            GenericResponseBody choreNotFound = new GenericResponseBody("Chore not found");
            return new ResponseEntity<>(choreNotFound, HttpStatus.NOT_FOUND);
        }
        Mission mission = missionOptional.get();
        Chore chore = choreOptional.get();

        // Check if this mission chore already exists
        if (missionChoreRepository.findByMissionIdAndChoreId(mission.getId(), chore.getId()).isPresent()){
            GenericResponseBody missionChoreAlreadyExists = new GenericResponseBody("MissionChore already exists");
            return new ResponseEntity<>(missionChoreAlreadyExists, HttpStatus.CONFLICT);
        }

        MissionChore missionChore = new MissionChore(mission, chore);
        missionChoreRepository.save(missionChore);

        return new ResponseEntity<GenericResponseBody>(new MissionChoreResponseBody(missionChore), HttpStatus.CREATED);
    }


    // get all Mission chores - Dev Only
    public Set<GenericResponseBody> getAllMissionChores()  {
        Iterable<MissionChore> missionChores = missionChoreRepository.findAll();
        Set<GenericResponseBody> missionChoreResponseBodies = new HashSet<>();
        missionChores.forEach(missionChore -> missionChoreResponseBodies.add(new MissionChoreResponseBody(missionChore)));
        return missionChoreResponseBodies;
    }

    // update ChoreMission
//    public GenericResponseBody updateMissionChore(Integer missionId, Integer choreId, Boolean isCompleted) {
    public GenericResponseBody updateMissionChore(Integer missionId, Integer choreId) {
        Optional<MissionChore> missionChoreOptional = missionChoreRepository.findByMissionIdAndChoreId(missionId, choreId);

        if (missionChoreOptional.isPresent()) {
            MissionChore missionChore = missionChoreOptional.get();
            missionChore.setIsCompleted(!missionChore.getIsCompleted());
            return new MissionChoreResponseBody(missionChoreRepository.save(missionChore));
        }
        return null;
    }
}
