import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //sterotype annotation, consider when hadnling incoming web requests
@EnableAutoConfiguration //assumes setting up webapp
public class MyApplication {

    @RequestMapping("/") //routing, any HTTP request with / path mapped to home
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
