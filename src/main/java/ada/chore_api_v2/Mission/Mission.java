package ada.chore_api_v2.Mission;


import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.MissionChore.MissionChore;
import ada.chore_api_v2.MissionChore.MissionChoreResponseBody;
import ada.chore_api_v2.User.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column
    private User user;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionChore> missionChores = new ArrayList<>();

    @Column(name = "recurrence", nullable = true)
    private String recurrence;

    @Column(name = "category", nullable = true)
    private String category;

    @Column(name = "date_started", nullable = false)
    private LocalDateTime dateStarted = LocalDateTime.now();

    @Column(name = "total_unredeemed_points", nullable = false)
    private Integer totalUnredeemedPoints = 0;

    @Column(name= "time_limit", nullable = true)
    private Long timeLimit;

    @Column(name = "time_elapsed", nullable = true)
    private Long timeElapsed = null;


    public Mission(User user, String recurrence, String category, Long timeLimit) {
        this.user = user;
        this.recurrence = recurrence;
        this.category = category;
        this.timeLimit = timeLimit;
    }

    public Mission() {}
    // Getters and setters
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MissionChore> getMissionChores() { return missionChores; }

    public void setMissionChores(List<MissionChore> missionChores) { this.missionChores = missionChores; }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDateStarted() { return dateStarted;}

    public void setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Integer getTotalUnredeemedPoints() {
        return totalUnredeemedPoints;
    }

    public void setTotalUnredeemedPoints(int totalUnredeemedPoints) { this.totalUnredeemedPoints = totalUnredeemedPoints; }

    public Long getTimeLimit() { return timeLimit; }

    public void setTimeLimit(Long timeLimit) { this.timeLimit = timeLimit; }

    public Long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public Set<GenericResponseBody> getMissionChoreResponses() {
        Set<GenericResponseBody> MissionChoreResponses= new HashSet<>();
        System.out.println("Length of mission.missionchoreList: " + missionChores.size());
        for (MissionChore missionChore : this.missionChores) {
            MissionChoreResponses.add(new MissionChoreResponseBody(missionChore));
        }
        System.out.println("Mission chore responses length: " + MissionChoreResponses.size());
        return MissionChoreResponses;
    }
}