package ada.chore_api_v2.UserReward;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRewardId implements Serializable {
    private Integer userId;
    private Integer rewardId;

    public UserRewardId() {}

    public UserRewardId(Integer userId, Integer rewardId) {
        this.userId = userId;
        this.rewardId = rewardId;
    }

    // Getters and setters
    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getRewardId() { return rewardId; }

    public void setRewardId(Integer rewardId) { this.rewardId = rewardId; }
}
