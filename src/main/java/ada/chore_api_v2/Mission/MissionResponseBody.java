package ada.chore_api_v2.Mission;

import ada.chore_api_v2.GenericResponseBody;

import java.time.LocalDateTime;

public class MissionResponseBody extends GenericResponseBody {
    private Integer missionId;
    private String category;
    private String recurrence;
    private Integer totalUnredeemedPoints;
    private LocalDateTime dateStarted;
    private Long timeLimit;
    private Long timeElapsed;
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


    public Integer getMissionId() { return missionId; }

    public String getCategory() { return category; }

    public String getRecurrence() { return recurrence; }

    public Integer getTotalUnredeemedPoints() { return totalUnredeemedPoints; }

    public Long getTimeLimit() { return timeLimit; }
    public LocalDateTime getDateStarted() { return dateStarted; }
    public Long getTimeElapsed() { return timeElapsed; }

    public Integer getUserId() { return userId; }
}