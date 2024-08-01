package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;

// BEGIN
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Bean;
import java.time.LocalTime;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Scope("prototype")
    @Bean
    public Daytime daytime() {
        LocalTime currentTime = LocalTime.now();
        if (currentTime.getHour() >= 6 && currentTime.getHour() <= 22) {
            return new Day();
        } else {
            return new Night();
        }
    }
    // END
}
