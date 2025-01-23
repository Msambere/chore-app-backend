package ada.chore_api_v2.Reward;

import ada.chore_api_v2.UserReward.UserReward;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(name = "in_mission", nullable = false)
    private Boolean inMission;

    @OneToMany(mappedBy ="reward", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<UserReward> userRewards = new HashSet<>();

    public Reward() {}

    public Reward(String name, String description, Boolean inMission) {
        this.name = name;
        this.description = description;
        this.inMission = inMission;
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

    public Set<UserReward> getUserRewards() { return userRewards; }

    public void setUserRewards(Set<UserReward> userRewards) { this.userRewards = userRewards; }
}
