package com.hibernate.ferreteria.repositorios;

import com.hibernate.ferreteria.entity.Articulos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Acceso a la BD

@Repository
public interface ArticulosRepositorio extends JpaRepository<Articulos, Long> { //Le pasamos la entidad (Articulos) y el tipo de dato de la PK (Long)
}
