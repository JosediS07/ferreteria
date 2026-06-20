package com.hibernate.ferreteria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="articulos")
//Las siguientes 3 anotaciones son necesarias para que se muestren en JSON al hacer la petición web
@Data //Genera automaticante los getters y setters de la entidad para que serialice a JSON
@NoArgsConstructor //Genera un constructor vacio automaticamente
@AllArgsConstructor //Genera un constroctor con todos los campos automaticamente

public class Articulos {

    @Id //Marca como primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera un valor automaticamente, es decir será un autoincrementado
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_articulo")
    private String nombreArticulo;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "stock")
    private Integer stock;

    @Override
    public String toString() {
        return "Articulos{" +
                "id=" + id +
                ", nombreArticulo='" + nombreArticulo + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
