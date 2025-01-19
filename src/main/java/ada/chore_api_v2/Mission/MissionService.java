package ada.chore_api_v2.Mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    // Create or update a mission
    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }

    // Get all missions with pagination
    public Page<Mission> getAllMissionsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findAll(pageable);
    }

    // Get a mission by ID
    public Optional<Mission> getMissionById(int id) {
        return missionRepository.findById(id);
    }

    // Get missions by user ID
    public List<Mission> getMissionsByUserId(int userId) {
        return missionRepository.findByUserId(userId);
    }

    // Get missions by user ID with pagination
    public List<Mission> getMissionsByUserIdWithPagination(int userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByUserId(userId, pageable);
    }

    // Get missions by category
    public List<Mission> getMissionsByCategory(String category) {
        return missionRepository.findByCategory(category);
    }

    // Get missions started after a specific date
    public List<Mission> getMissionsStartedAfter(LocalDateTime dateStarted) {
        return missionRepository.findByDateStartedAfter(dateStarted);
    }

    // Delete a mission by ID
    public void deleteMissionById(int id) {
        missionRepository.deleteById(id);
    }
}