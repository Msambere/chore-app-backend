package ada.chore_api_v2.Mission;

import java.time.LocalDateTime;
import java.time.Duration;


public class MissionResponseBody {
    private Integer missionId;
    private String category;
    private String recurrence;
    private Integer totalUnredeemedPoints;
    private LocalDateTime dateStarted;
    private Duration timeLimit;
    private Duration timeElapsed;
    private Integer userId;

    public MissionResponseBody(Mission mission) {
        this.missionId = mission.getId();
        this.category = mission.getCategory();
        this.recurrence = mission.getRecurrence();
        this.totalUnredeemedPoints = mission.getTotalUnredeemedPoints();
        this.dateStarted = mission.getDateStarted();
        this.timeLimit = mission.getTimeLimit();
        this.timeElapsed = mission.getTimeElapsed();
        this.userId = mission.getUser().getId();
    }

    public Integer getMissionId() {
        return missionId;
    }

    public String getCategory() {
        return category;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public Integer getTotalUnredeemedPoints() {
        return totalUnredeemedPoints;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }

    public Duration getTimeLimit() { return timeLimit; }

    public Duration getTimeElapsed() { return timeElapsed; }

    public Integer getUserId() {
        return userId;
    }
}