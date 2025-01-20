//package ada.chore_api_v2.Seeder;
//
//import ada.chore_api_v2.Mission.Mission;
//import ada.chore_api_v2.Mission.MissionRepository;
//import ada.chore_api_v2.User.User;
//import ada.chore_api_v2.User.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.Duration;
//import java.util.stream.IntStream;
//
//@Component
//public class DatabaseSeeder implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final MissionRepository missionRepository;
//
//    public DatabaseSeeder(UserRepository userRepository, MissionRepository missionRepository) {
//        this.userRepository = userRepository;
//        this.missionRepository = missionRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        // Create 5 Users
//        IntStream.range(1, 6).forEach(i -> {
//            User user = new User(
//                    "User" + i,
//                    "Last" + i,
//                    "user" + i,
//                    "user" + i + "@example.com"
//            );
//            userRepository.save(user);
//
//            // Create 10 Missions for each User
//            IntStream.range(1, 11).forEach(j -> {
//                Mission mission = new Mission(
//                        user,
//                        "Recurrence" + j,
//                        "Category" + j,
//                        LocalDateTime.now(),
//                        j * 10,
//                        Duration.ofHours(j)
//                );
//                missionRepository.save(mission);
//            });
//        });
//
//        System.out.println("Database seeded with 5 users and 10 missions per user!");
//    }
//}
