package ada.chore_api_v2.User;

import ada.chore_api_v2.GenericResponseBody;

import java.util.Set;

public class UserResponseBody extends GenericResponseBody {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Set<GenericResponseBody> chores;
    private Set<GenericResponseBody> missions;
    private Set<GenericResponseBody> rewards;



    public UserResponseBody(User user) {
        this.userId = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.chores = user.getChoreResponses();
        this.missions = user.getMissionResponses();
        this.rewards = user.getRewardResponses();
    }

    public Integer getUserId() { return userId;}

    public String getFirstName() { return firstName;}

    public void setFirstName(String firstName) { this.firstName = firstName;}

    public String getLastName() { return lastName;}

    public void setLastName(String lastName) { this.lastName = lastName;}

    public String getEmail() { return email;}

    public void setEmail(String email) { this.email = email;}

    public String getUsername() { return username;}

    public void setUsername(String username) { this.username = username;}

    public Set<GenericResponseBody> getChores() { return chores;}

    public void setChores(Set<GenericResponseBody> chores) { this.chores = chores;}

    public Set<GenericResponseBody> getMissions() { return missions;}

    public void setMissions(Set<GenericResponseBody> missions) { this.missions = missions;}

    public Set<GenericResponseBody> getRewards() { return rewards; }

    public void setRewards(Set<GenericResponseBody> rewards) { this.rewards = rewards; }
}
