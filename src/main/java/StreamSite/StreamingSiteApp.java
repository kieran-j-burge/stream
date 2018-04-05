package StreamSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StreamingSiteApp {

    public static void main(String[] args) {
        SpringApplication.run(StreamingSiteApp.class, args);
    }
}

