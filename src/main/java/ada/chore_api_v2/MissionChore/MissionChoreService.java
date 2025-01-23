package ada.chore_api_v2.MissionChore;

import ada.chore_api_v2.Mission.Mission;
import ada.chore_api_v2.Mission.MissionRepository;
import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    //create new ChoreMission
    public MissionChore createMissionChore( Integer missionId, Integer choreId) {
        Optional<Mission> missionOptional = missionRepository.findById(missionId);
        Optional<Chore> choreOptional = choreRepository.findById(choreId);

        if (missionOptional.isPresent() && choreOptional.isPresent()) {
            Mission mission = missionOptional.get();
            Chore chore = choreOptional.get();

            MissionChore missionChore = new MissionChore(mission, chore);
            return missionChoreRepository.save(missionChore);
        }
        return null;
    }


    // get all ChoreMissions
    public Iterable<MissionChore> getAllMissionChore()  {
        return missionChoreRepository.findAll();
    }

    // update ChoreMission
    public MissionChore updateMissionChore(Integer missionId, Integer choreId, Boolean isCompleted) {
        Optional<MissionChore> missionChoreOptional = missionChoreRepository.findByMissionIdAndChoreId(missionId, choreId);

        if (missionChoreOptional.isPresent()) {
            MissionChore missionChore = missionChoreOptional.get();
            missionChore.setIsCompleted(isCompleted);
            return missionChoreRepository.save(missionChore);
        }
        return null;
    }
}
