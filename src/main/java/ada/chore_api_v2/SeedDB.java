package ada.chore_api_v2;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import ada.chore_api_v2.Mission.Mission;
import ada.chore_api_v2.Mission.MissionRepository;
import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.stream.IntStream;


@Component
public class SeedDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ChoreRepository choreRepository;
    private final MissionRepository missionRepository;

    public SeedDB(UserRepository userRepository, ChoreRepository choreRepository, MissionRepository missionRepository) {
        this.userRepository = userRepository;
        this.choreRepository = choreRepository;
        this.missionRepository = missionRepository;
    }

    @Override
    public void run(String... args) {
        // Create 3 Users
        IntStream.range(1, 4).forEach(i -> {
            User user = new User(
                    "User" + i,
                    "Last" + i,
                    "user" + i,
                    "user" + i + "@usertable.com"
            );
            userRepository.save(user);

            // Create 3 Chores for each User
            IntStream.range(1, 4).forEach(j -> {
                Chore chore = new Chore(
                        " Chore Title " + j,
                        "Chore Description " + j,
                        "Chore Recurrence " + j % 3,
                        "Chore Category " + j % 2,
                        j*10L,
                        2,
                        user
                );
                choreRepository.save(chore);
            });
//             Create 3 chores for each User
            IntStream.range(1, 4).forEach(j -> {
                Mission mission = new Mission(
                        user,
                        "Mission Recurrence" + j,
                        "Mission Category" + j,
                        j*10L
                );
                missionRepository.save(mission);

            });

        });
        System.out.println("Database seeded with 3 users and 3 chores and 3 missions per user!");
    }
}




