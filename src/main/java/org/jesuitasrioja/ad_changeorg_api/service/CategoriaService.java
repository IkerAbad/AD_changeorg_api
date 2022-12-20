package org.jesuitasrioja.ad_changeorg_api.service;

import org.jesuitasrioja.ad_changeorg_api.domain.Categoria;
import org.jesuitasrioja.ad_changeorg_api.domain.Peticion;
import org.jesuitasrioja.ad_changeorg_api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService extends BaseService<Categoria,Long, CategoriaRepository>{

    @Autowired
    CategoriaRepository categoriaRepository;
    public void deleteById(Long id){
        if (findById(id).isPresent()){
            categoriaRepository.deleteById(id);
        }else {
            System.out.println("La peticion no existe");
        }
    }
    public Categoria findCategoria(Long id){
        Categoria peticion = categoriaRepository.findCategoriaById(id);
        return peticion;
    }
    public Categoria updatePeticionById(Categoria newCategoria){
        Optional<Categoria> categoria = null;
        try {
            categoria = categoriaRepository.findById(newCategoria.getId());
            if (categoria.isPresent()){
                categoriaRepository.save(newCategoria);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return categoria.get();
    }

    public Categoria addCategoria(Categoria newCategoria){
        Categoria categoria = newCategoria;
        categoriaRepository.save(categoria);
        return  categoria;
    }


}
