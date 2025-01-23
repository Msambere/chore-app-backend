package ada.chore_api_v2.MissionChore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissionChoreRepository extends JpaRepository<MissionChore, MissionChoreId> {
    Optional<MissionChore> findByMissionIdAndChoreId(Integer missionId, Integer choreId);
}
