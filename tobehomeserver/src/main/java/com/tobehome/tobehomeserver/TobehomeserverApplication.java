package com.tobehome.tobehomeserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableJpaAuditing
public class TobehomeserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(TobehomeserverApplication.class, args);
	}

}
