package com.robnarok.yoneban;

import com.robnarok.yoneban.services.ApiFetcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.robnarok.yoneban.repository")
public class YonebanApplication {

	public static void main(String[] args) {
		SpringApplication.run(YonebanApplication.class, args);
	}

}
