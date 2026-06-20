package com.hibernate.ferreteria.controladores;

import com.hibernate.ferreteria.dto.ArticulosDTO;
import com.hibernate.ferreteria.servicios.ArticulosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") //Anotación que permite configurar el CORS (Cross Origin Resource Sharing) que es un mecanismo de seguridad de los navegadores
// que impide que un sitio web haga solicitudes de tipo ajax a un dom diferente al que se originó. Al ponerle el param * le estamos diciendo que permita solicitudes
//de otro dominio u origen
@RestController
@RequestMapping("/api/articulos")
public class ArticulosController {

    @Autowired
    private ArticulosService servicio;

    @GetMapping()
    public List<ArticulosDTO>listar(){
        return servicio.listarArticulos();
    }

    @GetMapping("/{id}")
    public ArticulosDTO buscarArticulo(@PathVariable Long id){
        return servicio.buscarArticulo(id);
    }



    @PostMapping
    public ArticulosDTO insertar(@RequestBody ArticulosDTO dto){ //RequestBody convierte en obj Java el JSON que reciba
        return servicio.insertarArticulo(dto);
    }

    @PutMapping("/{id}")
    public ArticulosDTO modificar(@PathVariable Long id, @RequestBody ArticulosDTO dto) {
            return servicio.modificarArticulo(id, dto);

    }

    @DeleteMapping("/{id}")
    public String borrar(@PathVariable Long id){
        return servicio.eliminarArticulo(id);

    }



}
