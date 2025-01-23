package ada.chore_api_v2.MissionChore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionChoreRepository extends JpaRepository<MissionChore, MissionChoreId> {
}
