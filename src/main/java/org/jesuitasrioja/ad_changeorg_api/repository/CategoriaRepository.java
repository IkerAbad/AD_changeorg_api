package org.jesuitasrioja.ad_changeorg_api.repository;

import org.jesuitasrioja.ad_changeorg_api.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria,Long> {
    Categoria findCategoriaById(Long id);
}
