package ada.chore_api_v2.Mission;

import ada.chore_api_v2.User.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key column
    private User user;

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
    private Long timeElapsed =null;


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

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Integer getTotalUnredeemedPoints() {
        return totalUnredeemedPoints;
    }

    public void setTotalUnredeemedPoints(int totalUnredeemedPoints) {
        this.totalUnredeemedPoints = totalUnredeemedPoints;
    }
    public Long getTimeLimit() { return timeLimit; }


    public void setTimeLimit(Long timeLimit) { this.timeLimit = timeLimit; }

    public Long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}