package ada.chore_api_v2.Chore;

import ada.chore_api_v2.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoreRepository extends JpaRepository<Chore, Integer> {
    Chore findByTitleAndUser(String title, User user);
}
