package ada.chore_api_v2.ConnectionTest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin() //try to add origins using env variable to only only frontend url
@RequestMapping("")
public class HelloWorldController {
    public HelloWorldController() {}

    @GetMapping()
    public String root() {
        return "Welcome to the ChoreChamp App API!";
    }
}
