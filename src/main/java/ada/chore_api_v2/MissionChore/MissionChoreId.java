package ada.chore_api_v2.MissionChore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class MissionChoreId implements Serializable {
//    @Column(name = "mission_id")
    private Integer missionId;

//    @Column(name = "chore_id")
    private Integer choreId;

    public MissionChoreId() {}

    public MissionChoreId(Integer missionId, Integer choreId)  {
        this.missionId = missionId;
        this.choreId = choreId;
    }

    // Getters and setters
    public Integer getMissionId() { return missionId; }

    public void setMissionId(Integer missionId) { this.missionId = missionId; }

    public Integer getChoreId() { return choreId; }

    public void setChoreId(Integer choreId) { this.choreId = choreId; }

}
