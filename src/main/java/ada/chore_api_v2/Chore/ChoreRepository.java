package ada.chore_api_v2.Chore;

import ada.chore_api_v2.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ChoreRepository extends JpaRepository<Chore, Integer> {
    Chore findByTitleAndUser(String title, User user);
    ArrayList<Chore> findByRecurrenceAndCategoryAndUser(String recurrence, String category, User user);
    ArrayList<Chore> findByCategoryAndUser(String category, User user);
    ArrayList<Chore> findByRecurrenceAndUser(String recurrence, User user);
    ArrayList<Chore> findByUser(User user);

}
