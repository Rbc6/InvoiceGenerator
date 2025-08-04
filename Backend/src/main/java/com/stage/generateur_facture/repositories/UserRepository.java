package com.stage.generateur_facture.repositories;

import com.stage.generateur_facture.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
