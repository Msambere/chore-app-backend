package ada.chore_api_v2.Chore;

import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChoreService {
    private final ChoreRepository choreRepository;
    private final UserRepository userRepository;

    public ChoreService(ChoreRepository choreRepository, UserRepository userRepository) {
        this.choreRepository = choreRepository;
        this.userRepository = userRepository;
    }

    public Iterable<Chore> getAllChores() {
        return choreRepository.findAll();
    }

    public  ChoreResponseBody createChore(int userId, Chore choreRequest) {
         Optional<User> foundUser =userRepository.findById(userId);
         if(foundUser.isPresent()) {
            choreRequest.setUser(foundUser.get());
            return new ChoreResponseBody(choreRepository.save(choreRequest));
         }
        return null;
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
