package cs5834.luckyPaws;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import cs5834.luckyPaws.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableEncryptableProperties
public class LuckyPawsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LuckyPawsApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
	}

	}


