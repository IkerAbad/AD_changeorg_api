package org.jesuitasrioja.ad_changeorg_api.controller;


import org.jesuitasrioja.ad_changeorg_api.domain.Categoria;
import org.jesuitasrioja.ad_changeorg_api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAllCategories(){
        return new ResponseEntity<>(categoriaService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaService.findById(id);
        return new ResponseEntity<>(categoria.get(), HttpStatus.OK);
    }

    @PostMapping("/categorias/add")
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria newCategoria){
        return new ResponseEntity<>(categoriaService.addCategoria(newCategoria), HttpStatus.OK);
    }

    @PutMapping("/categorias/update")
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.updatePeticionById(categoria),HttpStatus.OK);
    }

    @DeleteMapping("/categorias/delete/{id}")
    public void deleteCategoria(@PathVariable Long id){
        categoriaService.deleteById(id);
    }

}
