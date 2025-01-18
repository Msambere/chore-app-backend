package ada.chore_api_v2.Mission;

import ada.chore_api_v2.User.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime dateStarted;

    @Column(name = "total_unredeemed_points", nullable = false)
    private int totalUnredeemedPoints;

    @Column(name = "time_elapsed", nullable = true)
    private int timeElapsed; // Or a suitable data type for interval

    public Mission() {
        this.dateStarted = LocalDateTime.now();
        this.totalUnredeemedPoints = 0;
    }

    public Mission(User user, String recurrence, String category, LocalDateTime dateStarted, int totalUnredeemedPoints, int timeElapsed) {
        this.user = user;
        this.recurrence = recurrence;
        this.category = category;
        this.dateStarted = LocalDateTime.now();
        this.totalUnredeemedPoints = 0;
        this.timeElapsed = timeElapsed;
    }

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

    public int getTotalUnredeemedPoints() {
        return totalUnredeemedPoints;
    }

    public void setTotalUnredeemedPoints(int totalUnredeemedPoints) {
        this.totalUnredeemedPoints = totalUnredeemedPoints;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}