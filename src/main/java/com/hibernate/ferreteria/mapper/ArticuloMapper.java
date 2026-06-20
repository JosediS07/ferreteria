package com.hibernate.ferreteria.mapper;


import com.hibernate.ferreteria.dto.ArticulosDTO;
import com.hibernate.ferreteria.entity.Articulos;

public class ArticuloMapper {

    public static ArticulosDTO toDTO(Articulos articulo){
        return new ArticulosDTO(
                articulo.getId(),
                articulo.getNombreArticulo(),
                articulo.getPrecio(),
                articulo.getStock()
        );
    }


    public static Articulos toEntity(ArticulosDTO dto){
        Articulos articulo=new Articulos();
        articulo.setId(dto.getId());
        articulo.setNombreArticulo(dto.getNombreArticulo());
        articulo.setPrecio(dto.getPrecio());
        articulo.setStock(dto.getStock());
        return articulo;

    }


}
