package ada.chore_api_v2.Mission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {
    List<Mission> findByUserId(int userId);

    List<Mission> findByCategory(String category);

    List<Mission> findByDateStartedAfter(LocalDateTime dateStarted);

    Page<Mission> findByUserId(int userId, Pageable pageable);
}