package com.danidu.coronatrackingsite;

import com.danidu.coronatrackingsite.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CoronaTrackingSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaTrackingSiteApplication.class, args);
	}

}
