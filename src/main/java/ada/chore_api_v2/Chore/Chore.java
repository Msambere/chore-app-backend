package ada.chore_api_v2.Chore;

import jakarta.persistence.*;

@Entity
@Table(name="chores")
public class Chore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title", nullable = false, unique = true)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "recurrence", nullable = false)
    private String recurrence;

    @Column(name="category", nullable = false)
    private String category;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "difficulty", nullable = false)
    private int difficulty;

    public Chore() {}
    public Chore(String title, String description, String recurrence, String category, int duration, int difficulty) {
        this.title = title;
        this.description = description;
        this.recurrence = recurrence;
        this.category = category;
        this.duration = duration;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
