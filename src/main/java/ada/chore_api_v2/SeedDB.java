package ada.chore_api_v2;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import ada.chore_api_v2.Mission.Mission;
import ada.chore_api_v2.Mission.MissionRepository;
import ada.chore_api_v2.MissionChore.MissionChore;
import ada.chore_api_v2.MissionChore.MissionChoreRepository;
import ada.chore_api_v2.Reward.Reward;
import ada.chore_api_v2.Reward.RewardRepository;
import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;


@Component
public class SeedDB implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ChoreRepository choreRepository;
    private final MissionRepository missionRepository;
    private final MissionChoreRepository missionChoreRepository;
    private final RewardRepository rewardRepository;

    public SeedDB(UserRepository userRepository, ChoreRepository choreRepository, MissionRepository missionRepository, MissionChoreRepository missionChoreRepository, RewardRepository rewardRepository) {
        this.userRepository = userRepository;
        this.choreRepository = choreRepository;
        this.rewardRepository = rewardRepository;
        this.missionRepository = missionRepository;
        this.missionChoreRepository = missionChoreRepository;
    }

    @Override
    public void run(String... args) {
        //Create Demo user
        User Michelle = new User("Michelle", "Obmama", "FirstLady4eva", "mObama@gmail.com");
        userRepository.save(Michelle);

        // Give demo user chores
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

        // Give demo user rewards
        List<Reward> rewardList = new ArrayList<>();
        rewardList.add(new Reward("Pet play break", "Take 10 mins to play with Pepper", true, 2, Michelle));
        rewardList.add(new Reward("SNS recharge", "Check social media for 10 mins", true, 3, Michelle));
        rewardList.add(new Reward("Dance party", "Dance to your favorite song", true, 1, Michelle));
        rewardList.add(new Reward("Mindfulnes", "Do one mindfulness activity", true, 2, Michelle));
        rewardList.add(new Reward("Feed the beast", "Have a small snack", true, 3, Michelle));
        rewardRepository.saveAll(rewardList);

        // Create 3 missions for demo user
        Mission mission1 = new Mission(Michelle, null, null, null);
        mission1.setDateStarted(LocalDateTime.now().minusDays(5L));
        missionRepository.saveAndFlush(mission1);
        Set<Chore> choreSet1 = getMatchingChores(null, null,  null, Michelle);
        System.out.println("Num of found chores: " + choreSet1.size());
        for (Chore chore : choreSet1) {
            MissionChore newMissionChore = new MissionChore(mission1,chore);
            missionChoreRepository.save(newMissionChore);
        }

        Mission mission2 = new Mission(Michelle, "Daily", null, null);
        mission2.setDateStarted(LocalDateTime.now().minusDays(3L));
        missionRepository.saveAndFlush(mission2);
        Set<Chore> choreSet2 = getMatchingChores("Daily", null , null , Michelle);
        System.out.println("Num of found chores: " + choreSet2.size());
        for (Chore chore : choreSet2) {
            MissionChore newMissionChore = new MissionChore(mission2,chore);
            missionChoreRepository.save(newMissionChore);
        }


        Mission mission3 = new Mission(Michelle, null, "Kitchen", null);
        mission3.setDateStarted(LocalDateTime.now().minusDays(1L));
        missionRepository.saveAndFlush(mission3);
        Set<Chore> choreSet3 = getMatchingChores(null, "Kitchen",null, Michelle);
        System.out.println("Num of found chores: " + choreSet3.size());
        for (Chore chore : choreSet3) {
            MissionChore newMissionChore = new MissionChore(mission3,chore);
            missionChoreRepository.save(newMissionChore);
        }
        System.out.println("Database seeded!");
    }


    public Set<Chore> getMatchingChores(String recurrence, String category, Long timeLimit, User user) {
        System.out.println("Finding matching chores");
        Set<Chore> chores = new HashSet<>();
        ArrayList<Chore> choreOptions = null;
        // Filter chores by recurrence and category values
        if (recurrence != null && category != null) {
            System.out.println("Matching by recurrence and category");
            choreOptions = choreRepository.findByRecurrenceAndCategoryAndUser(recurrence, category, user);
        }
        else if(recurrence == null && category != null) {
            System.out.println("Matching by category");
            choreOptions = choreRepository.findByCategoryAndUser(category, user);
        }
        else if (recurrence != null) {
            System.out.println("Matching by recurrence");
            choreOptions = choreRepository.findByRecurrenceAndUser(recurrence, user);
        } else {
            System.out.println("Returning all chores");
            choreOptions = choreRepository.findByUser(user);
        }
        // Sort List from oldest to newest
        Collections.sort(choreOptions, Comparator.comparing(Chore::getLastCompletedDate));


        // Add sorted chores to set without exceeding timeLimit
        if (timeLimit == null) {
            timeLimit = Long.MAX_VALUE;
        }
        long currentLengthTime = 0L;
        for(Chore chore : choreOptions) {
            if (chore.getDuration() + currentLengthTime <= timeLimit) {
                chores.add(chore);
                currentLengthTime += chore.getDuration();
                if (currentLengthTime == timeLimit){
                    break;
                }
            }
        }
        return chores;
    }

}

