package org.jesuitasrioja.ad_changeorg_api.controller;

import org.jesuitasrioja.ad_changeorg_api.domain.Peticion;
import org.jesuitasrioja.ad_changeorg_api.domain.User;
import org.jesuitasrioja.ad_changeorg_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>  getAllUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/users/firmas/{usuario_id}")
    public ResponseEntity<List<Peticion>> findAllPeticionesFirmadas(@PathVariable Long usuario_id){
        return new ResponseEntity<>(userService.findAllPeticiociones(usuario_id),HttpStatus.OK);
    }

    @PostMapping("/users/add")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.OK);
    }

    @PutMapping("/users/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return new ResponseEntity<>(userService.updateUserById(user),HttpStatus.OK);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }
}
