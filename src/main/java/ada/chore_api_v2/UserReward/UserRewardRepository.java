package ada.chore_api_v2.UserReward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRewardRepository extends JpaRepository<UserReward, UserRewardId> {
//    Optional<UserReward> findByUserRewardId(Integer userId, Integer missionId);
}

