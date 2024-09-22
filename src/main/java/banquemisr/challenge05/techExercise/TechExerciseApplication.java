package banquemisr.challenge05.techExercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
public class TechExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechExerciseApplication.class, args);
	}

}
