package org.jesuitasrioja.ad_changeorg_api.repository;

import org.jesuitasrioja.ad_changeorg_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {}
