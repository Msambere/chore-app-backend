package ada.chore_api_v2.MissionChore;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Mission.Mission;
import jakarta.persistence.*;

@Entity
@Table(name = "mission_chore")
public class MissionChore {

    @EmbeddedId
    private MissionChoreId id;

    @ManyToOne
    @MapsId("missionId")
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne
    @MapsId("choreId")
    @JoinColumn(name = "chore_id", nullable = false)
    private Chore chore;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;

    public MissionChore() {}

    public MissionChore(Mission mission, Chore chore) {
        this.id = new MissionChoreId(mission.getId(), chore.getId());
        this.mission = mission;
        this.chore = chore;
    }

    // Getters and setters
    public MissionChoreId getId() { return id;}

    public void setId(MissionChoreId id) { this.id = id; }

    public Mission getMission() { return mission; }

    public void setMission(Mission mission) { this.mission = mission; }

    public Chore getChore() { return chore; }

    public void setChore(Chore chore) { this.chore = chore; }

    public Boolean getIsCompleted() { return isCompleted; }

    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}
