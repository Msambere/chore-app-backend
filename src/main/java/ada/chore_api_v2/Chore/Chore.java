package ada.chore_api_v2.Chore;

import ada.chore_api_v2.User.User;
import ada.chore_api_v2.MissionChore.MissionChore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="chores")
public class Chore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="title", nullable = false)
    @NotBlank
    private String title;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "recurrence", nullable = false)
    @NotBlank
    private String recurrence;

    @Column(name="category", nullable = false)
    @NotBlank
    private String category;

    @Column(name = "duration", nullable = false)
    @NotNull
    private Long duration;

    @Column(name = "difficulty", nullable = false)
    @Min(1)
    @Max(3)
    @NotNull
    private Integer difficulty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @Valid
    private User user;

    @OneToMany(mappedBy = "chore", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<MissionChore> missionChores = new HashSet<>();

    public Chore() {}

    public Chore(String title, String description, String recurrence, String category, Long duration, Integer difficulty, User user) {
        this.title = title;
        this.description = description;
        this.recurrence = recurrence;
        this.category = category;
        this.duration = duration;
        this.difficulty = difficulty;
        this.user = user;
    }

    public int getId() { return this.id; }

    public String getTitle() { return this.title;}

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getRecurrence() { return this.recurrence; }

    public void setRecurrence(String recurrence) { this.recurrence = recurrence; }

    public String getCategory() { return this.category; }

    public void setCategory(String category) { this.category = category; }

    public Long getDuration() { return this.duration; }

    public void setDuration(Long duration) { this.duration = duration; }

    public Integer getDifficulty() { return this.difficulty; }

    public void setDifficulty(Integer difficulty) { this.difficulty = difficulty; }

    public User getUser() { return this.user; }

    public void setUser(User user) { this.user = user; }

    public Set<MissionChore> getChoreMissions() { return this.missionChores; }

    public void setChoreMissions(Set<MissionChore> missionChores) { this.missionChores = missionChores; }

    public LocalDateTime getLastCompletedDate() {
       LocalDateTime latestDate = null;
       if (!missionChores.isEmpty()){
           for (MissionChore missionChore : missionChores) {
               LocalDateTime missionDate = missionChore.getMission().getDateStarted();
               if (latestDate == null){ latestDate = missionDate; }
               else if (latestDate.isBefore(missionDate)) {latestDate = missionDate;}
           }
       } else{
            latestDate = LocalDateTime.now();
       }

        return latestDate;
    }
}
