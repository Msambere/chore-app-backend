package ada.chore_api_v2.ChoreMission;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import ada.chore_api_v2.Mission.Mission;
import ada.chore_api_v2.Mission.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChoreMissionService {

    private final ChoreMissionRepository choreMissionRepository;
    private final ChoreRepository choreRepository;
    private final MissionRepository missionRepository;

    public ChoreMissionService(ChoreMissionRepository choreMissionRepository, ChoreRepository choreRepository, MissionRepository missionRepository) {
        this.choreMissionRepository = choreMissionRepository;
        this.choreRepository = choreRepository;
        this.missionRepository = missionRepository;
    }

    //create new ChoreMission
    public ChoreMission createChoreMission(Integer choreId, Integer missionId, Boolean isCompleted) {
        Optional<Chore> choreOptional = choreRepository.findById(choreId);
        Optional<Mission> missionOptional = missionRepository.findById(missionId);

        if (choreOptional.isPresent() && missionOptional.isPresent()) {
            Chore chore = choreOptional.get();
            Mission mission = missionOptional.get();

            ChoreMission choreMission = new ChoreMission(chore, mission, isCompleted);
            return choreMissionRepository.save(choreMission);
        }
        return null;
    }

    // get all ChoreMissions
    public Iterable<ChoreMission> getAllChoreMission() {
        return choreMissionRepository.findAll();
    }

    // update ChoreMission
    public ChoreMission updateChoreMission(Integer choreMissionId, Boolean isCompleted) {
        Optional<ChoreMission> choreMissionOptional = choreMissionRepository.findById(choreMissionId);

        if (choreMissionOptional.isPresent()) {
            ChoreMission choreMission = choreMissionOptional.get();
            choreMission.setIsCompleted(isCompleted);
            return choreMissionRepository.save(choreMission);
        }

        return null;
    }

}
