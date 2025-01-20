package ada.chore_api_v2.Chore;

import ada.chore_api_v2.User.User;

import java.time.Duration;

public class ChoreResponseBody {
    public String title;
    public String description;
    public String recurrence;
    public String category;
    public Duration duration;
    public Integer difficulty;
    public Integer userId;

    public ChoreResponseBody(Chore chore) {
        this.title = chore.getTitle();
        this.description = chore.getDescription();
        this.recurrence = chore.getRecurrence();
        this.category = chore.getCategory();
//        this.duration = chore.getDuration().toMinutes();
        this.duration = chore.getDuration();
        this.difficulty = chore.getDifficulty();
        this.userId = chore.getUser().getId();
    }
}
