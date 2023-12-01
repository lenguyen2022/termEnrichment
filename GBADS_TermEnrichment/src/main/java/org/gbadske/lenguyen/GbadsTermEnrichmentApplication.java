package org.gbadske.lenguyen;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class GbadsTermEnrichmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(GbadsTermEnrichmentApplication.class, args);
	}
}
