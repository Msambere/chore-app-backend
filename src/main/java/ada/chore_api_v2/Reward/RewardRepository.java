package ada.chore_api_v2.Reward;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RewardRepository extends JpaRepository<Reward, Integer> {
    Set<Reward> findByUserId(Integer userId);

    Set<Reward> findByNameAndDescriptionAndInMissionAndPointsNeeded(String name, String description, Boolean inMission, Integer pointsNeeded);
}
