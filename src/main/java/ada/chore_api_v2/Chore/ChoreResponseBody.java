package ada.chore_api_v2.Chore;

import ada.chore_api_v2.GenericResponseBody;


public class ChoreResponseBody extends GenericResponseBody {
    private String title;
    private String description;
    private String recurrence;
    private String category;
    private Long duration;
    private Integer difficulty;
    private Integer userId;
    private Integer choreId;

    public ChoreResponseBody(Chore chore) {
        this.title = chore.getTitle();
        this.description = chore.getDescription();
        this.recurrence = chore.getRecurrence();
        this.category = chore.getCategory();
        this.duration = chore.getDuration();
        this.difficulty = chore.getDifficulty();
        this.userId = chore.getUser().getId();
        this.choreId = chore.getId();
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getRecurrence() { return recurrence; }

    public void setRecurrence(String recurrence) { this.recurrence = recurrence; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public Long getDuration() { return duration; }

    public void setDuration(Long duration) { this.duration = duration; }

    public Integer getDifficulty() { return difficulty; }

    public void setDifficulty(Integer difficulty) { this.difficulty = difficulty; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getChoreId() { return choreId; }

    public void setChoreId(Integer choreId) { this.choreId = choreId; }
}
