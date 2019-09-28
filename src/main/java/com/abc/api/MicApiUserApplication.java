package com.abc.api;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class MicApiUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicApiUserApplication.class, args);
	}
	
	@Bean
	public AcceptHeaderLocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver acceptHeaderLocaleResolver =  new AcceptHeaderLocaleResolver();
		acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
		return  acceptHeaderLocaleResolver;
	}
	
}
