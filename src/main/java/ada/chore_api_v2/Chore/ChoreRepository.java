package ada.chore_api_v2.Chore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoreRepository extends JpaRepository<Chore, Integer> {
}
