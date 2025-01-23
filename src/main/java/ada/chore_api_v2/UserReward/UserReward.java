package ada.chore_api_v2.UserReward;

import ada.chore_api_v2.Reward.Reward;
import ada.chore_api_v2.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_rewards")
public class UserReward {

    @EmbeddedId
    private UserRewardId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("rewardId")
    @JoinColumn(name = "reward_id", nullable = false)
    private Reward reward;

    @Column(name = "points_needed", nullable = false)
    private Integer pointsNeeded;

    public UserReward() {}

    public UserReward(User user, Reward reward, Integer pointsNeeded) {
        this.id = new UserRewardId(user.getId(), reward.getId());
        this.user = user;
        this.reward = reward;
        this.pointsNeeded = pointsNeeded;
    }

    // Getters and setters
    public UserRewardId getId() { return id; }

    public void setId(UserRewardId id) { this.id = id; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Reward getReward() { return reward; }

    public void setReward(Reward reward) { this.reward = reward; }

    public Integer getPointsNeeded() { return pointsNeeded; }

    public void setPointsNeeded(Integer pointsNeeded) { this.pointsNeeded = pointsNeeded; }
}
