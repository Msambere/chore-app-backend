package ada.chore_api_v2.User;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreResponseBody;

import java.util.HashSet;
import java.util.Set;

public class UserResponseBody {
    public String firstName;
    public String lastName;
    public String email;
    public String username;
    public Set<ChoreResponseBody> chores;



    public UserResponseBody(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.chores = user.getChoreResponses();
    }

}
