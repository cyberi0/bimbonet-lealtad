package com.bimbonet.bimbonet_lealtad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BimbonetLealtadApplication {

	public static void main(String[] args) {
		SpringApplication.run(BimbonetLealtadApplication.class, args);
	}

}
