package org.jesuitasrioja.ad_changeorg_api.repository;

import com.zaxxer.hikari.util.IsolationLevel;
import org.jesuitasrioja.ad_changeorg_api.domain.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource
public interface PeticionesRepository extends JpaRepository<Peticion,Long>, JpaSpecificationExecutor<Peticion> {

    @Query("SELECT p FROM Peticion p WHERE p.estadoPeticion = 0")
    List<Peticion> findAllByEstadoPeticion();

    @Query("select p from Peticion p where p.user.id= :id")
    List<Peticion> findPeticionByUser(Long id);



}
