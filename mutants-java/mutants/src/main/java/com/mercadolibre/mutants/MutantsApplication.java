package com.mercadolibre.mutants;

import com.mercadolibre.mutants.utils.H2ServerStarter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutantsApplication {

	public static void main(String[] args) {
		H2ServerStarter.startH2Server();
		SpringApplication.run(MutantsApplication.class, args);
	}
}
