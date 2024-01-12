package com.raf.sk_user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing // obezbadjuje da Spring moze da kontrolise CRUD operacije
@EnableScheduling
public class SkUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkUserServiceApplication.class, args);
	}

}
