package ada.chore_api_v2.MissionChore;

import ada.chore_api_v2.GenericResponseBody;

public class MissionChoreResponseBody extends GenericResponseBody {
    private Integer missionId;
    private Integer choreId;
    private String choreName;
    private Long duration;
    private Integer points;
    private Boolean isCompleted;

    public MissionChoreResponseBody(MissionChore missionChore) {
        this.missionId = missionChore.getMission().getId();
        this.choreId = missionChore.getChore().getId();
        this.choreName = missionChore.getChore().getTitle();
        this.duration = missionChore.getChore().getDuration();
        this.points = missionChore.getChore().getDifficulty();
        this.isCompleted = missionChore.getIsCompleted();
    }

    public Integer getMissionId() { return missionId;}

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public Integer getChoreId() {
        return choreId;
    }

    public void setChoreId(Integer choreId) {
        this.choreId = choreId;
    }

    public String getChoreName() {
        return choreName;
    }

    public void setChoreName(String choreName) {
        this.choreName = choreName;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {this.isCompleted = completed;}

    public Long getDuration() { return duration;}

    public void setDuration(Long duration) { this.duration = duration;}

    public Integer getPoints() { return points; }

    public void setPoints(Integer points) { this.points = points;}
}
