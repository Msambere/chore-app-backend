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

    public Chore getChoreById(Integer id) {
        Optional<Chore> chore = choreRepository.findById(id);
        if (chore.isPresent()) {
            return chore.get();
        }
        return null;
    }

    public ChoreResponseBody updateChore(Integer id, Chore choreRequest) {
        Optional<Chore> chore = choreRepository.findById(id);
        if(chore.isPresent()) {
            Chore updatedChore = chore.get();
            if (choreRequest.getTitle() != null) {
                updatedChore.setTitle(choreRequest.getTitle());
            }
            if (choreRequest.getDescription() != null) {
                updatedChore.setDescription(choreRequest.getDescription());
            }
            if (choreRequest.getDifficulty() != null){
                updatedChore.setDifficulty(choreRequest.getDifficulty());
            }
            if (choreRequest.getDuration() != null){
                updatedChore.setDuration(choreRequest.getDuration());
            }
            if (choreRequest.getRecurrence() != null) {
                updatedChore.setRecurrence(choreRequest.getRecurrence());
            }
            if (choreRequest.getCategory() != null) {
                updatedChore.setCategory(choreRequest.getCategory());
            }
            return new ChoreResponseBody(choreRepository.save(updatedChore));
        }
        return null;
    }

    public String deleteChoreById(Integer id) {
        Optional<Chore> chore = choreRepository.findById((id));
        if (chore.isPresent()) {
            choreRepository.delete(chore.get());
            return "Chore deleted";
        }
        return "Chore not found";
    }

}
