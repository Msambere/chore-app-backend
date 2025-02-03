package ada.chore_api_v2.User;

import ada.chore_api_v2.Chore.Chore;
import ada.chore_api_v2.Chore.ChoreRepository;
import ada.chore_api_v2.Chore.ChoreResponseBody;
import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.Reward.Reward;
import ada.chore_api_v2.Reward.RewardRepository;
import ada.chore_api_v2.Reward.RewardResponseBody;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NewUserService {
    private final ChoreRepository choreRepository;
    private final RewardRepository rewardRepository;

    public NewUserService(ChoreRepository choreRepository, RewardRepository rewardRepository) {
        this.choreRepository = choreRepository;
        this.rewardRepository = rewardRepository;
    }

    public Set<GenericResponseBody> addDefaultRewardsToNewUser(User newUser){
        List<Reward> rewardList = new ArrayList<>();
        rewardList.add(new Reward("Pet play break", "Take 10 mins to play with Pepper", true, 2, newUser));
        rewardList.add(new Reward("SNS recharge", "Check social media for 10 mins", true, 3, newUser));
        rewardList.add(new Reward("Dance party", "Dance to your favorite song", true, 2, newUser));
        rewardList.add(new Reward("Mindfulness", "Do one mindfulness activity", true, 2, newUser));
        rewardList.add(new Reward("Feed the beast", "Have a small snack", true, 3, newUser));
        rewardList.add(new Reward("Power Nap", "Take a 15-minute nap", true, 5, newUser));
        rewardList.add(new Reward("Read a Chapter", "Read a chapter from your favorite book", true, 4, newUser));
        rewardList.add(new Reward("Gaming Break", "Play a game for 15 minutes", true, 5, newUser));
        rewardList.add(new Reward("Bubble Break", "Blow bubbles for a stress-free moment", true, 2, newUser));
        rewardList.add(new Reward("Stretch it Out", "Do a quick 5-minute stretch session", true, 2, newUser));
        rewardList.add(new Reward("Movie Time", "Watch 20 minutes of a show or movie", true, 6, newUser));
        rewardList.add(new Reward("Go for a Walk", "Take a short walk outside", true, 4, newUser));
        rewardList.add(new Reward("Tea & Chill", "Make yourself a cup of tea and relax", true, 3, newUser));
        rewardList.add(new Reward("Art Break", "Draw, color, or doodle for fun", true, 3, newUser));
        rewardList.add(new Reward("Listen to Music", "Relax and listen to your favorite song", true, 2, newUser));

        // Save rewards
        rewardRepository.saveAll(rewardList);
        Set<GenericResponseBody> rewardResponses = new HashSet<>();
        for (Reward reward : rewardList) {
            rewardResponses.add(new RewardResponseBody(reward));
        }
        return rewardResponses;
    }

    public Set<GenericResponseBody> addDefaultChoresToNewUser(User newUser) {
        List<Chore> choreList = new ArrayList<>();

        // Daily Chores - Kitchen
        choreList.add(new Chore("Wash Dishes", "Clean and put away dishes after meals", "Daily", "Kitchen", 15L, 2, newUser));
        choreList.add(new Chore("Wipe Kitchen Counters", "Sanitize countertops and clear crumbs", "Daily", "Kitchen", 5L, 1, newUser));
        choreList.add(new Chore("Sweep Kitchen Floor", "Remove dirt and crumbs from floor", "Daily", "Kitchen", 10L, 1, newUser));
        choreList.add(new Chore("Empty Kitchen Trash", "Remove food waste to prevent odor", "Daily", "Kitchen", 5L, 1, newUser));

        // Daily Chores - Bathroom
        choreList.add(new Chore("Scrub Bathroom Sink & Counter", "Clean surfaces and remove buildup", "Daily", "Bathroom", 10L, 2, newUser));
        choreList.add(new Chore("Wipe Down Bathroom Mirror", "Remove streaks and water spots", "Daily", "Bathroom", 5L, 1, newUser));
        choreList.add(new Chore("Change Bathroom Hand Towels", "Ensure fresh, clean towels", "Daily", "Bathroom", 5L, 1, newUser));
        choreList.add(new Chore("Clean Toilet Bowl", "Scrub with toilet cleaner to maintain hygiene", "Daily", "Bathroom", 10L, 2, newUser));

        // Daily Chores - Bedroom
        choreList.add(new Chore("Make Bed", "Tidy and arrange bedding", "Daily", "Bedroom", 5L, 1, newUser));
        choreList.add(new Chore("Tidy Bedroom", "Pick up clutter and organize", "Daily", "Bedroom", 10L, 2, newUser));
        choreList.add(new Chore("Put Away Clothes", "Fold or hang laundry", "Daily", "Bedroom", 10L, 2, newUser));
        choreList.add(new Chore("Vacuum Bedroom Floor", "Keep floors dust-free", "Daily", "Bedroom", 15L, 2, newUser));

        // Daily Chores - Pet Care
        choreList.add(new Chore("Clean Litter Box", "Scoop waste and refresh litter", "Daily", "Pet Care", 10L, 2, newUser));
        choreList.add(new Chore("Refill Pet Food & Water Bowls", "Ensure pets have fresh food and water", "Daily", "Pet Care", 5L, 1, newUser));
        choreList.add(new Chore("Wipe Down Pet Feeding Area", "Clean pet food bowls and surfaces", "Daily", "Pet Care", 5L, 1, newUser));
        choreList.add(new Chore("Check for Pet Accidents", "Spot clean any messes", "Daily", "Pet Care", 5L, 2, newUser));

        // Daily Chores - Misc
        choreList.add(new Chore("Take Out Trash", "Remove household garbage to outdoor bins", "Daily", "Misc", 10L, 2, newUser));
        choreList.add(new Chore("Disinfect Door Handles & Light Switches", "Sanitize high-touch areas", "Daily", "Misc", 5L, 1, newUser));
        choreList.add(new Chore("Sort Mail & Papers", "Organize daily incoming documents", "Daily", "Misc", 10L, 1, newUser));
        choreList.add(new Chore("Water Houseplants", "Keep indoor plants healthy", "Daily", "Misc", 5L, 1, newUser));

        // Weekly Chores - Kitchen
        choreList.add(new Chore("Mop Kitchen Floor", "Deep clean kitchen floors", "Weekly", "Kitchen", 15L, 3, newUser));
        choreList.add(new Chore("Clean Microwave", "Wipe down inside and outside", "Weekly", "Kitchen", 10L, 2, newUser));
        choreList.add(new Chore("Wipe Kitchen Cabinets", "Remove grease and dust", "Weekly", "Kitchen", 10L, 2, newUser));
        choreList.add(new Chore("Clean Kitchen Sink & Drain", "Scrub and deodorize", "Weekly", "Kitchen", 10L, 2, newUser));

        // Weekly Chores - Bathroom
        choreList.add(new Chore("Scrub Bathtub & Shower", "Remove soap scum and mildew", "Weekly", "Bathroom", 15L, 3, newUser));
        choreList.add(new Chore("Wash Bathroom Rugs", "Refresh mats and rugs", "Weekly", "Bathroom", 10L, 2, newUser));
        choreList.add(new Chore("Sanitize Toilet Seat & Handle", "Wipe down with disinfectant", "Weekly", "Bathroom", 5L, 1, newUser));
        choreList.add(new Chore("Clean Bathroom Vent", "Remove dust and grime", "Weekly", "Bathroom", 10L, 2, newUser));

        // Weekly Chores - Bedroom
        choreList.add(new Chore("Vacuum Bedroom", "Remove dust and debris from carpets/floors", "Weekly", "Bedroom", 15L, 2, newUser));
        choreList.add(new Chore("Change Bed Sheets", "Replace with fresh sheets", "Weekly", "Bedroom", 10L, 2, newUser));
        choreList.add(new Chore("Organize Closet", "Tidy and declutter clothing", "Weekly", "Bedroom", 15L, 2, newUser));
        choreList.add(new Chore("Dust Bedroom Surfaces", "Clean furniture and shelves", "Weekly", "Bedroom", 10L, 1, newUser));

        // Weekly Chores - Pet Care
        choreList.add(new Chore("Bathe Pet", "Wash and groom pet", "Weekly", "Pet Care", 20L, 3, newUser));
        choreList.add(new Chore("Wash Pet Bedding & Toys", "Sanitize pet-related items", "Weekly", "Pet Care", 15L, 2, newUser));
        choreList.add(new Chore("Trim Pet’s Nails", "Maintain healthy nail length", "Weekly", "Pet Care", 10L, 3, newUser));
        choreList.add(new Chore("Brush Pet", "Reduce shedding and maintain coat", "Weekly", "Pet Care", 10L, 2, newUser));

        // Weekly Chores - Misc
        choreList.add(new Chore("Do Laundry", "Wash and dry a load of clothes", "Weekly", "Misc", 20L, 2, newUser));
        choreList.add(new Chore("Dust Furniture", "Wipe down surfaces", "Weekly", "Misc", 10L, 2, newUser));
        choreList.add(new Chore("Wipe Down Electronics", "Clean TVs, monitors, and remotes", "Weekly", "Misc", 10L, 1, newUser));
        choreList.add(new Chore("Sweep Porch or Entryway", "Keep entrance tidy", "Weekly", "Misc", 10L, 2, newUser));

        // Monthly Chores - Kitchen
        choreList.add(new Chore("Deep Clean Refrigerator", "Throw out old food, wipe shelves", "Monthly", "Kitchen", 20L, 3, newUser));
        choreList.add(new Chore("Organize Pantry", "Sort and declutter food items", "Monthly", "Kitchen", 20L, 3, newUser));
        choreList.add(new Chore("Descale Coffee Maker", "Remove hard water buildup", "Monthly", "Kitchen", 10L, 2, newUser));
        choreList.add(new Chore("Wipe Down Oven & Stovetop", "Remove grease and spills", "Monthly", "Kitchen", 15L, 3, newUser));

        // Monthly Chores - Bathroom
        choreList.add(new Chore("Scrub Bathroom Walls & Grout", "Remove mold and grime", "Monthly", "Bathroom", 20L, 3, newUser));
        choreList.add(new Chore("Wash Shower Curtain & Liner", "Prevent mildew buildup", "Monthly", "Bathroom", 15L, 2, newUser));
        choreList.add(new Chore("Organize Bathroom Cabinets", "Dispose of expired items", "Monthly", "Bathroom", 10L, 2, newUser));
        choreList.add(new Chore("Clean Out Bathroom Drains", "Prevent clogs with drain cleaner", "Monthly", "Bathroom", 10L, 2, newUser));

        // Monthly Chores - Bedroom
        choreList.add(new Chore("Vacuum Under Bed", "Remove dust and debris", "Monthly", "Bedroom", 15L, 2, newUser));
        choreList.add(new Chore("Flip & Rotate Mattress", "Ensure even wear", "Monthly", "Bedroom", 20L, 3, newUser));
        choreList.add(new Chore("Clean Bedroom Windows", "Wash inside and outside glass", "Monthly", "Bedroom", 15L, 2, newUser));
        choreList.add(new Chore("Declutter Nightstands", "Remove unnecessary items", "Monthly", "Bedroom", 10L, 2, newUser));

        // Monthly Chores - Misc
        choreList.add(new Chore("Wipe Down Walls & Doors", "Remove scuffs and dirt", "Monthly", "Misc", 15L, 2, newUser));
        choreList.add(new Chore("Clean Light Fixtures & Ceiling Fans", "Dust and wipe down", "Monthly", "Misc", 10L, 2, newUser));
        choreList.add(new Chore("Clean Window Tracks", "Remove dirt and grime", "Monthly", "Misc", 15L, 2, newUser));
        choreList.add(new Chore("Check & Replace Air Filters", "Ensure good air quality", "Monthly", "Misc", 10L, 2, newUser));
        choreList.add(new Chore("Wash Baseboards", "Remove dust and dirt", "Monthly", "Misc", 15L, 2, newUser));
        choreList.add(new Chore("Inspect & Clean Vacuum Filter", "Ensure vacuum efficiency", "Monthly", "Misc", 10L, 1, newUser));

        // Save chores
        choreRepository.saveAllAndFlush(choreList);
        Set<GenericResponseBody> choreResponses = new HashSet<>();
        for (Chore chore : choreList) {
            choreResponses.add(new ChoreResponseBody(chore));
        }
        return choreResponses;
    }
}
