package br.edu.ifrs.riogrande.tads.cobaia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CobaiaApplication {
	public static void main(String[] args) {
		System.out.println("Rodando ...");
		SpringApplication.run(CobaiaApplication.class, args);
	}
}
