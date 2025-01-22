package ada.chore_api_v2.Chore;

import ada.chore_api_v2.GenericResponseBody;
import ada.chore_api_v2.User.User;
import ada.chore_api_v2.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ChoreService {
    private final ChoreRepository choreRepository;
    private final UserRepository userRepository;

    public ChoreService(ChoreRepository choreRepository, UserRepository userRepository) {
        this.choreRepository = choreRepository;
        this.userRepository = userRepository;
    }

    // Create new Chore
    public  GenericResponseBody createChore(int userId, Chore choreRequest) {
         Optional<User> foundUser =userRepository.findById(userId);
         if(foundUser.isPresent()) {
            choreRequest.setUser(foundUser.get());
            // Add logic to check if this chore already exists by title
            return new ChoreResponseBody(choreRepository.save(choreRequest));
         }
        return null;
    }

    // Get all Chores
    public Set<GenericResponseBody> getAllChores() {
        Iterable<Chore> chores = choreRepository.findAll();
        Set<GenericResponseBody> choreResponseBodies = new HashSet<>();
        chores.forEach(chore -> {choreResponseBodies.add(new ChoreResponseBody(chore));});
        return choreResponseBodies;
    }


    // Get chore by id
    public GenericResponseBody getChoreById(Integer id) {
        Optional<Chore> chore = choreRepository.findById(id);
        return chore.map(ChoreResponseBody::new).orElse(null);
    }

    // Update a chore
    public GenericResponseBody updateChore(Integer id, Chore choreRequest) {
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

    public GenericResponseBody deleteChoreById(Integer id) {
        Optional<Chore> chore = choreRepository.findById((id));
        if (chore.isPresent()) {
            choreRepository.delete(chore.get());
            return new GenericResponseBody("Chore deleted successfully");
        }
        return new GenericResponseBody("Chore not found");
    }

}
