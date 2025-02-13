package com.example.clienteservice;

import com.example.clienteservice.model.Cliente;
import com.example.clienteservice.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClienteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ClienteRepository repository) {
		return args -> {
			repository.save(new Cliente(null, "C", "12345678", "Juan", "Carlos", "Pérez", "Gómez", "123456789", "Calle 1", "Bogotá", "juan@example.com"));
			repository.save(new Cliente(null, "P", "87654321", "Ana", "María", "López", "Díaz", "987654321", "Calle 2", "Medellín", "ana@example.com"));
			repository.save(new Cliente(null, "P", "123456789", "Hector", "Hector", "Muñoz", "Muñoz", "2312312341", "Calle 2", "Medellín", "ana@example.com"));
		};
	}

}
