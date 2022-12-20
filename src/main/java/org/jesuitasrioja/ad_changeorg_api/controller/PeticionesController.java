package org.jesuitasrioja.ad_changeorg_api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import org.jesuitasrioja.ad_changeorg_api.domain.Categoria;
import org.jesuitasrioja.ad_changeorg_api.domain.Peticion;
import org.jesuitasrioja.ad_changeorg_api.payload.request.PeticionDto;
import org.jesuitasrioja.ad_changeorg_api.payload.response.Response;
import org.jesuitasrioja.ad_changeorg_api.service.CategoriaService;
import org.jesuitasrioja.ad_changeorg_api.service.FileStorageService;
import org.jesuitasrioja.ad_changeorg_api.service.PeticionesService;
import org.jesuitasrioja.ad_changeorg_api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PeticionesController{


    @Autowired
    PeticionesService peticionesService;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserService userService;
    @Autowired
    CategoriaService categoriaService;

/*    @GetMapping("/peticiones")
    public ResponseEntity<List<Peticion>> listarPeticiones(){
        return new ResponseEntity<>(peticionesService.findAll(), HttpStatus.OK);
    }*/

    /*@GetMapping("/peticiones")
    public ResponseEntity<Page<Peticion>>
    listaPeticiones(@PageableDefault(size=10, page = 0) Pageable pageable) {
        Page<Peticion> peticiones = peticionesService.findAll(pageable);
        if ( pageable.getPageNumber() > peticiones.getTotalPages()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(peticiones, HttpStatus.OK);
    }*/
    @GetMapping("/peticiones")
    public ResponseEntity<Page<Peticion>>
    listaPeticiones(@PageableDefault(size=10, page = 0) Pageable pageable,
                    @SearchSpec Specification<Peticion> specs) {
        //logger.info("inicio listar peticiones paginadas");
        Page<Peticion> peticiones =
                peticionesService.getPeticiones(pageable,specs);
        if ( pageable.getPageNumber() > peticiones.getTotalPages()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //logger.info("fin listar peticiones paginadas");
        return new ResponseEntity<>(peticiones, HttpStatus.OK);
    }

    @GetMapping("/peticiones/{id}")
    public ResponseEntity<Peticion> findById(@PathVariable Long id) {
        Optional<Peticion> peticion = peticionesService.findById(id);
        return new ResponseEntity<>(peticion.get(), HttpStatus.OK);
    }

    @DeleteMapping("/peticiones/delete/{id}")
    public ResponseEntity<Response> deletePeticion(@PathVariable Long id){
        peticionesService.deleteById(id);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }

    @PutMapping("peticiones/edit/{id}")
    public ResponseEntity<Peticion> updateById(@RequestBody PeticionDto newPeticion, @PathVariable Long id) {
        Peticion peticion = new ModelMapper().map(newPeticion,Peticion.class);
        peticion.setCategoria(categoriaService.findCategoria(newPeticion.getCategoria_id()));
        peticionesService.updatePeticionById(peticion,id);
        return new ResponseEntity<>(peticion,HttpStatus.OK);
    }
    @PutMapping("/peticiones/{id}")
    public ResponseEntity<Peticion> cambiarEstadoPeticion(@PathVariable Long id){
        return new ResponseEntity<>(peticionesService.cambiarEstadoPeticion(id),HttpStatus.OK);
    }
    @PutMapping("/peticiones/{peticion_id}/{user_id}")
    public ResponseEntity<Peticion> addFirmante(@PathVariable Long peticion_id, @PathVariable Long user_id){
        return new ResponseEntity<>(peticionesService.addFirmante(peticion_id,user_id),HttpStatus.OK);
    }

   @PostMapping("/peticiones/create/{user_id}/{categoria_id}")
        public ResponseEntity<Peticion> addPeticion(@RequestBody Peticion newPeticion, @PathVariable Long user_id, @PathVariable Long categoria_id){
        Peticion peticion = peticionesService.addPeticion(newPeticion,user_id,categoria_id);
        return new ResponseEntity<>(peticion,HttpStatus.OK);
    }
    @PostMapping("/peticiones/{user_id}/{categoria_id}")
    public ResponseEntity<Peticion> addPeticion(@RequestParam("peticion")
                                                String jsonObject, @RequestParam("file") MultipartFile file, @PathVariable Long
                                                        user_id, @PathVariable Long categoria_id) {
        //logger.info("buscar usuario asociado");
        Peticion peticion=null;
        try{
            String fileName = fileStorageService.storeFile(file);
            peticion = objectMapper.readValue(jsonObject, Peticion.class);
            peticion.setPhoto(fileName);
            Peticion peticion1 = peticionesService.addPeticion(peticion,user_id,categoria_id);
            return new ResponseEntity<>(peticion1, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("peticion/{id}/uploads")
    public void downloadPhoto(HttpServletResponse httpServletResponse, @PathVariable Long id){
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setHeader("Content-Disposition","attachment; filename=imagen.jpeg");

    }


}
