package ada.chore_api_v2.Mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    // Helper to convert a single Mission to MissionResponseBody
    private MissionResponseBody toResponseBody(Mission mission) {
        return new MissionResponseBody(mission); // Assumes MissionResponseBody has a constructor
    }

    // Helper to convert a list of Missions to a list of MissionResponseBody
    private List<MissionResponseBody> toResponseBodyList(List<Mission> missions) {
        return missions.stream()
                .map(this::toResponseBody)
                .collect(Collectors.toList());
    }

    // Create or update a mission
    public MissionResponseBody saveMission(Mission mission) {
        Mission savedMission = missionRepository.save(mission);
        return toResponseBody(savedMission);
    }

    // Get all missions with pagination
    public Page<MissionResponseBody> getAllMissionsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findAll(pageable).map(this::toResponseBody);
    }

    // Get a mission by ID
    public Optional<MissionResponseBody> getMissionById(int id) {
        return missionRepository.findById(id)
                .map(this::toResponseBody);
    }

    // Get missions by user ID
    public List<MissionResponseBody> getMissionsByUserId(int userId) {
        List<Mission> missions = missionRepository.findByUserId(userId);
        return toResponseBodyList(missions);
    }

    // Get missions by user ID with pagination
    public Page<MissionResponseBody> getMissionsByUserIdWithPagination(int userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return missionRepository.findByUserId(userId, pageable)
                .map(this::toResponseBody);
    }

    // Get missions by category
    public List<MissionResponseBody> getMissionsByCategory(String category) {
        List<Mission> missions = missionRepository.findByCategory(category);
        return toResponseBodyList(missions);
    }

    // Get missions started after a specific date
    public List<MissionResponseBody> getMissionsStartedAfter(LocalDateTime dateStarted) {
        List<Mission> missions = missionRepository.findByDateStartedAfter(dateStarted);
        return toResponseBodyList(missions);
    }

    // Delete a mission by ID
    public void deleteMissionById(int id) {
        missionRepository.deleteById(id);
    }
}