package ada.chore_api_v2.ConnectionTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HelloWorld")
public class HelloWorldController {
    public HelloWorldController() {}

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/")
    public String root() {
        return "Welcome to the Chore App API!";
    }
}
