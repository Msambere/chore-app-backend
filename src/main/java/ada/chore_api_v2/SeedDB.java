package ada.chore_api_v2;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
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

    public SeedDB(UserRepository userRepository, ChoreRepository choreRepository) {
        this.userRepository = userRepository;
        this.choreRepository = choreRepository;
    }

    @Override
    public void run(String... args) {
        // Create 5 Users
        IntStream.range(1, 6).forEach(i -> {
            User user = new User(
                    "User" + i,
                    "Last" + i,
                    "user" + i,
                    "user" + i + "@usertable.com"
            );
            userRepository.save(user);

            // Create 10 Chores for each User
            IntStream.range(1, 11).forEach(j -> {
                Duration duration = Duration.ofMinutes(j*5L);
                Chore chore = new Chore(
                        "Title " + j,
                        "Description " +j,
                        "Recurrence " + j%3,
                        "Category " + j%2,
//                        duration.toMinutes(),
                        duration,
                        2,
                        user
                );
                choreRepository.save(chore);
            });
        });

        System.out.println("Database seeded with 5 users and 10 chores per user!");
    }
}




