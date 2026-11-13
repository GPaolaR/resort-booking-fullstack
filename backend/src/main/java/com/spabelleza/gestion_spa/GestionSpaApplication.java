package com.spabelleza.gestion_spa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class GestionSpaApplication {

	public static void main(String[] args) {
		// The business runs on Colombia time; date logic assumes this zone
		TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
		SpringApplication.run(GestionSpaApplication.class, args);
	}

}