package com.jaroso.trazabilidad_productos.repositories;



import com.jaroso.trazabilidad_productos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUserName(String userName);
    //User findUserByUserName (String username);
}
