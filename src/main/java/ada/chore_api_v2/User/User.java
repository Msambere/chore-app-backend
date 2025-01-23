package ada.chore_api_v2.User;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreResponseBody;
import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.Mission.Mission;
import ada.chore_api_v2.Mission.MissionResponseBody;
import ada.chore_api_v2.MissionChore.MissionChore;
import ada.chore_api_v2.UserReward.UserReward;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="firstName", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotBlank
    private String lastName;

    @Column(name = "username", unique = true, nullable = false)
    @NotBlank
    private String username;

    @Column(name="email", nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy ="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Chore> chores = new HashSet<>();

    @OneToMany(mappedBy ="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Mission> missions = new HashSet<>();

    @OneToMany(mappedBy ="user", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<UserReward> userRewards = new HashSet<>();

    public User(String firstName, String lastName, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    public User() {}

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Chore> getChores() {
        return chores;
    }

    public void addChore(Chore chore) {
        this.chores.add(chore);
    }

    public Set<UserReward> getUserRewards() { return this.userRewards; }

    public void setUserRewards(Set<UserReward> userRewards) { this.userRewards = userRewards; }

    public Set<GenericResponseBody> getChoreResponses() {
        Set<GenericResponseBody> choreResponses= new HashSet<>();
        for(Chore chore : this.chores) {
            choreResponses.add(new ChoreResponseBody(chore));
        }
        return choreResponses;
    }

    public Set<GenericResponseBody> getMissionResponses() {
        Set<GenericResponseBody> missionResponses= new HashSet<>();
        for(Mission mission : this.missions) {
            missionResponses.add(new MissionResponseBody(mission));
        }
        return missionResponses;
    }
}

