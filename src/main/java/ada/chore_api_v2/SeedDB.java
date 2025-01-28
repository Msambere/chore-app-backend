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
import java.util.ArrayList;
import java.util.List;
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
        //Create user 1
        User Michelle = new User("Michelle", "Obmama", "FirstLady4eva", "mObama@gmail.com");
        userRepository.save(Michelle);

        // Give user 1 chores
        List<Chore> choreList = new ArrayList<>();
        choreList.add(new Chore("Sweep Floor", "description", "Daily", "Kitchen", 10L, 1, Michelle));
        choreList.add(new Chore("Mop Floor", "description", "Daily", "Kitchen", 25L, 3, Michelle));
        choreList.add(new Chore("Wash Dishes", "description", "Daily", "Kitchen", 15L, 2, Michelle));
        choreList.add(new Chore("Wash 1 load of laundry", "start one load of laundry", "Daily", "Misc", 5L, 1, Michelle));
        choreList.add(new Chore("Make bed", "description", "Daily", "Bedroom", 5L, 1, Michelle));
        choreList.add(new Chore("Meal prep", "description", "Weekly", "Misc", 30L, 3, Michelle));
        choreList.add(new Chore("Clean Bathroom", "description", "Weekly", "Misc", 30L, 3, Michelle));
        choreList.add(new Chore("Take out the garbage", "description", "Weekly", "Misc", 10L, 2, Michelle));
        choreList.add(new Chore("Clean gutters", "description", "Monthly", "Outdoors", 40L, 3, Michelle));
        choreList.add(new Chore("Mow the lawn", "description", "Weekly", "Outdoors", 40L, 3, Michelle));
        choreList.add(new Chore("Dust ceiling fans", "description", "Monthly", "Misc", 25L, 2, Michelle));
        choreList.add(new Chore("Clean Fridge", "description", "Monthly", "Kitchen", 25L, 3, Michelle));
        choreList.add(new Chore("Fold Laundry", "description", "Weekly", "Misc", 15L, 1, Michelle));
        choreRepository.saveAllAndFlush(choreList);

        // Give user 1 rewards



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
                        j*5L,
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
        System.out.println("Database seeded!");
    }
}




