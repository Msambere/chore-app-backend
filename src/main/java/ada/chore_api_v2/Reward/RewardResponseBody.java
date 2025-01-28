package ada.chore_api_v2.Reward;

import ada.chore_api_v2.GenericResponseBody;

public class RewardResponseBody extends GenericResponseBody {
    private Integer rewardId;
    private String name;
    private String description;
    private Boolean inMission;
    private Integer pointsNeeded;
    private Integer userId;

    public RewardResponseBody(Reward reward) {
        this.rewardId = reward.getId();
        this.name = reward.getName();
        this.description = reward.getDescription();
        this.inMission = reward.getInMission();
        this.pointsNeeded = reward.getPointsNeeded();
        this.userId = reward.getUser().getId();
    }

    // Getters and setters
    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInMission() {
        return inMission;
    }

    public void setInMission(Boolean inMission) {
        this.inMission = inMission;
    }

    public Integer getPointsNeeded() {
        return pointsNeeded;
    }

    public void setPointsNeeded(Integer pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}