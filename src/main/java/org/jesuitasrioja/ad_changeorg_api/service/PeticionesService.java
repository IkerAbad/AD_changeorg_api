package org.jesuitasrioja.ad_changeorg_api.service;


import com.sipios.springsearch.anotation.SearchSpec;
import org.jesuitasrioja.ad_changeorg_api.domain.EstadoPeticion;
import org.jesuitasrioja.ad_changeorg_api.domain.Peticion;
import org.jesuitasrioja.ad_changeorg_api.domain.User;
import org.jesuitasrioja.ad_changeorg_api.payload.request.PeticionDto;
import org.jesuitasrioja.ad_changeorg_api.repository.CategoriaRepository;
import org.jesuitasrioja.ad_changeorg_api.repository.PeticionesRepository;
import org.jesuitasrioja.ad_changeorg_api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeticionesService  extends BaseService<Peticion, Long, PeticionesRepository> {

    @Autowired
    PeticionesRepository peticionesRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UserRepository userRepository;



    public List<Peticion> findAllByEstadoPeticion() {
        List<Peticion> peticiones = (List<Peticion>)
                peticionesRepository.findAllByEstadoPeticion();
        return peticiones;
    }

    public void deleteById(Long id) {
        if (findById(id).isPresent()) {
            peticionesRepository.deleteById(id);
        } else {
            System.out.println("La peticion no existe");
        }
    }

    public Peticion updatePeticionById(Peticion newPeticion, Long id) {


        Peticion peticion = newPeticion;

        if (peticionesRepository.findById(id).isPresent()) {
                peticion.setId(id);

                peticionesRepository.save(peticion);
            }
    return peticion;
    }
    public Page<Peticion> getPeticiones(Pageable pageable, @SearchSpec
    Specification<Peticion> specs){
        return peticionesRepository.findAll(specs, pageable);
    }

    public Peticion addPeticion(Peticion newPeticion, Long user_id, Long categoria_id) {
        Peticion peticion = null;
        if (categoriaRepository.findById(categoria_id).isPresent() && userRepository.findById(user_id).isPresent()) {
            peticion = newPeticion;
            peticion.setUser(userRepository.findById(user_id).get());
            peticion.setCategoria(categoriaRepository.findById(categoria_id).get());
            peticion = newPeticion;
            peticionesRepository.save(peticion);
        }
        return peticion;
    }
    public Peticion cambiarEstadoPeticion(Long id){
        Optional<Peticion>peticion = peticionesRepository.findById(id);
        if (peticion.isPresent()){
            if (peticion.get().getEstadoPeticion().equals(EstadoPeticion.PENDIENTE)){
                peticion.get().setEstadoPeticion(EstadoPeticion.ACEPTADA);
            }
        }
        return peticion.get();
    }

    public Peticion addFirmante(Long user_id, Long peticion_id){
        Optional<User> user = userRepository.findById(user_id);
        Optional<Peticion> peticion = peticionesRepository.findById(peticion_id);

        if (peticion.isPresent() && user.isPresent()){
            peticion.get().setFirmantes(peticion.get().getFirmantes()+1);
            peticion.get().getUsuarios().add(user.get());
            peticionesRepository.save(peticion.get());
        }
        return peticion.get();
    }

    public List<Peticion> findPeticionByUser(Long user_id) {
        return peticionesRepository.findPeticionByUser(user_id);
    }
}

