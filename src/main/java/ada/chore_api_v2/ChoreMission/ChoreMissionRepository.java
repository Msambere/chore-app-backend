package ada.chore_api_v2.ChoreMission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoreMissionRepository extends JpaRepository<ChoreMission, Integer> {
}
