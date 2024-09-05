package com.example.RegisterLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import com.example.RegisterLogin.Service.migration.DataMigrationService;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class RegisterLoginApplication  implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(RegisterLoginApplication.class, args);
	}
	@Autowired
	private DataMigrationService dataMigrationService;

	@Override
	public void run(String... args) {
		dataMigrationService.migrateData();
		System.out.println("Data migration completed.");
	}
}
