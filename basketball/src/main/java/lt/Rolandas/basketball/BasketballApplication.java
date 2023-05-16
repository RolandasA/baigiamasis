package lt.Rolandas.basketball;

import com.github.javafaker.Faker;
import lt.Rolandas.basketball.model.User;
import lt.Rolandas.basketball.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BasketballApplication {

	public static void main(String[] args) {

		SpringApplication.run(BasketballApplication.class, args);
	}

}
