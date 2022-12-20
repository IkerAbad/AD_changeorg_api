package org.jesuitasrioja.ad_changeorg_api.service;

import org.jesuitasrioja.ad_changeorg_api.domain.Categoria;
import org.jesuitasrioja.ad_changeorg_api.domain.Peticion;
import org.jesuitasrioja.ad_changeorg_api.domain.User;
import org.jesuitasrioja.ad_changeorg_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService extends BaseService<User,Long, UserRepository>{

    @Autowired
    UserRepository userRepository;
    @Autowired
    PeticionesService peticionesService;

    public void deleteById(Long id){
        if (findById(id).isPresent()){
            userRepository.deleteById(id);
        }else {
            System.out.println("La peticion no existe");
        }
    }

    public User updateUserById(User newUser){
        Optional<User> user = null;
        try {
            user = userRepository.findById(newUser.getId());
            if (user.isPresent()){
                userRepository.save(newUser);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return user.get();
    }

    public User addUser(User newUser){
        User user = newUser;
        userRepository.save(user);
        return user;
    }
    public List<Peticion> findAllPeticiociones(Long user_id){
        List<Peticion> peticions = null;
        Optional<User> petion = userRepository.findById(user_id);
        if (petion.isPresent()){
            peticions=petion.get().getPeticions();
            // peticions= peticionesService.findPeticionByUser(user_id);

        }
        return peticions;
    }

}
