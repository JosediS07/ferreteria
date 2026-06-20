package com.hibernate.ferreteria;

import com.hibernate.ferreteria.entity.Articulos;
import com.hibernate.ferreteria.repositorios.ArticulosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FerreteriaApplication implements CommandLineRunner {

	@Autowired
	private ArticulosRepositorio repositorio; //Creamos el objeto repositorio que no necesitamos inicializar gracias a que autowired

	public static void main(String[] args) {
		SpringApplication.run(FerreteriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicacion iniciada correctamente");
		List<Articulos>articulos=repositorio.findAll();
		articulos.stream()
				.forEach(System.out::println);
	}
}
