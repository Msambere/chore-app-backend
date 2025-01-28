package ada.chore_api_v2.Reward;

import ada.chore_api_v2.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "in_mission", nullable = false)
    private Boolean inMission;

    @Column(name = "points_needed", nullable = false)
    private Integer pointsNeeded;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @Valid
    private User user;

    public Reward() {}

    public Reward(String name, String description, Boolean inMission, Integer pointsNeeded, User user) {
        this.name = name;
        this.description = description;
        this.inMission = inMission;
        this.pointsNeeded = pointsNeeded;
        this.user = user;
    }

    // Getters and setters
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Boolean getInMission() { return inMission; }

    public void setInMission(Boolean inMission) { this.inMission = inMission; }

    public Integer getPointsNeeded() { return pointsNeeded; }

    public void setPointsNeeded(Integer pointsNeeded) { this.pointsNeeded = pointsNeeded; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}