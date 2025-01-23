package ada.chore_api_v2;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import ada.chore_api_v2.Mission.Mission;
import ada.chore_api_v2.Mission.MissionRepository;
import ada.chore_api_v2.MissionChore.MissionChore;
import ada.chore_api_v2.MissionChore.MissionChoreRepository;
import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.IntStream;


@Component
public class SeedDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ChoreRepository choreRepository;
    private final MissionRepository missionRepository;
    private final MissionChoreRepository missionChoreRepository;

    public SeedDB(UserRepository userRepository, ChoreRepository choreRepository, MissionRepository missionRepository, MissionChoreRepository missionChoreRepository) {
        this.userRepository = userRepository;
        this.choreRepository = choreRepository;
        this.missionRepository = missionRepository;
        this.missionChoreRepository = missionChoreRepository;
    }

    @Override
    public void run(String... args) {
        // Create 5 Users
        IntStream.range(1, 4).forEach(i -> {
            User user = new User(
                    "User" + i,
                    "Last" + i,
                    "user" + i,
                    "user" + i + "@usertable.com"
            );
            userRepository.save(user);

            // Create 10 Chores for each User
            IntStream.range(1, 4).forEach(j -> {
                Duration duration = Duration.ofMinutes(j * 5L);
                Chore chore = new Chore(
                        " Chore Title " + j,
                        "Chore Description " + j,
                        "Chore Recurrence " + j % 3,
                        "Chore Category " + j % 2,
//                        duration.toMinutes(),
                        duration,
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

                IntStream.range(1, 4).forEach(k -> {
                    Chore chore = choreRepository.findById(k).orElse(null); // Fetch the Chore
                    if (chore != null) {
                        MissionChore missionChore = new MissionChore(
                                mission,
                                chore
                        );
                        missionChoreRepository.save(missionChore);
                    }
                });
            });
        });
        System.out.println("Database seeded with 3 users and 3 chores and 3 missions per user!");
    }
}




