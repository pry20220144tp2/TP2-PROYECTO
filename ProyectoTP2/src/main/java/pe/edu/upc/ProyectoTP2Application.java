package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProyectoTP2Application implements CommandLineRunner{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTP2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		String password = "web";

		for (int i = 0; i < 1; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}*/
	}
}
