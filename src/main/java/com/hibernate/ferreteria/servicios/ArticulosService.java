package com.hibernate.ferreteria.servicios;

import com.hibernate.ferreteria.dto.ArticulosDTO;
import com.hibernate.ferreteria.entity.Articulos;
import com.hibernate.ferreteria.mapper.ArticuloMapper;
import com.hibernate.ferreteria.repositorios.ArticulosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//Capa media entre repositorio y controller con la lógica de negocio

@Service
public class ArticulosService {

    @Autowired
    private ArticulosRepositorio repositorio;

    public List<ArticulosDTO> listarArticulos(){
        return repositorio.findAll().stream()
                .map(ArticuloMapper::toDTO)
                .toList();
    }

    public ArticulosDTO buscarArticulo(Long id){
        Optional<Articulos> existe=repositorio.findById(id);
        if (existe.isPresent()){
            Articulos articulo=existe.get();
            return ArticuloMapper.toDTO(articulo);
        }else{
            throw new RuntimeException("No existe el articulo con el id "+"'"+id+"'");
        }
    }




    //Para insertar desde postman usamos el metodo post con la url localhost:8080/api/articulos y en body con formato raw le pasamos un json del
    //objeto a insertar
    // {
    //        "nombreArticulo": "Elemento prueba",
    //        "precio": 50.0,
    //        "stock": 33
    //    }

    public ArticulosDTO insertarArticulo(ArticulosDTO dto){
        Articulos articulo= ArticuloMapper.toEntity(dto); //Convertimos nuestro objeto dto en una entidad para la BD
        Articulos insertado = repositorio.save(articulo); //Guardamos el articulo en la BD o lo modificamos en caso de que ya exista
        return ArticuloMapper.toDTO(insertado); //Devolvemos el DTO del objeto insertado para el cliente

    }



    public ArticulosDTO modificarArticulo(Long id,ArticulosDTO dto){ //Pasamos Id del articulo a modificar y el dto modificado

        Optional<Articulos> existe= repositorio.findById(id); //Usamos la clase Optinal para recuper todos los valores menos los nulos
        if (existe.isPresent()){//Si el valor esta presente hacemos esto
            Articulos articulo=existe.get(); //Lo recuperamos

            //Settemos los nuevos campos que hemo recibido
            articulo.setNombreArticulo(dto.getNombreArticulo());
            articulo.setPrecio(dto.getPrecio());
            articulo.setStock(dto.getStock());

            //Insertamos en la tabla
            Articulos actualizado=repositorio.save(articulo);

            //Devolvemos el dto actualizado
            return ArticuloMapper.toDTO(actualizado);
        }else {
            throw new RuntimeException("Articulo no encontrado con id: "+id);
        }
    }


    public String eliminarArticulo(Long id){
        if (repositorio.existsById(id)){
            repositorio.deleteById(id);
            return "El articulo con el ID: "+ "'"+id+"'"+" ha sido eliminado correctamente";
        } else {
            return "El articulo con el ID: "+ "'"+id+"'"+" no existe";
        }

    }


}
