package ada.chore_api_v2.Chore;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChoreService {
    private final ChoreRepository choreRepository;

    public ChoreService(ChoreRepository choreRepository) {
        this.choreRepository = choreRepository;
    }

    public Chore createChore(Chore chore) {
        return choreRepository.save(chore);
    }

    public Iterable<Chore> getAllChores() {
        return choreRepository.findAll();
    }

    public Chore getChoreById(long id) {
        Optional<Chore> chore = choreRepository.findById(id);
        if (chore.isPresent()) {
            return chore.get();
        }
        return null;
    }

    public String deleteChoreById(int id) {
        Optional<Chore> chore = choreRepository.findById((long) id);
        if (chore.isPresent()) {
            choreRepository.delete(chore.get());
            return "Chore deleted";
        }
        return "Chore not found";
    }

}
