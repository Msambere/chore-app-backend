package ada.chore_api_v2.ChoreMission;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Mission.Mission;
import jakarta.persistence.*;

@Entity
@Table(name = "chore_mission")
public class ChoreMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "chore_id", nullable = false)
    private Chore chore;

    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted;

    public ChoreMission() {}

    public ChoreMission(Chore chore, Mission mission, Boolean isCompleted) {
        this.chore = chore;
        this.mission = mission;
        this.isCompleted = isCompleted;
    }

    // Getters and setters
    public Integer getId() { return id; }

    public Chore getChore() { return chore; }

    public void setChore(Chore chore) { this.chore = chore; }

    public Mission getMission() { return mission; }

    public void setMission(Mission mission) { this.mission = mission; }

    public Boolean getIsCompleted() { return isCompleted; }

    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }
}
