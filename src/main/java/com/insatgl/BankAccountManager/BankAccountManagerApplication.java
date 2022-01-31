package com.insatgl.BankAccountManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BankAccountManagerApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(BankAccountManagerApplication.class, args);
	}

}
