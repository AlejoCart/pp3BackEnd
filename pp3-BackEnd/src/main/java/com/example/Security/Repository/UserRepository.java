package com.example.Security.Repository;

import com.example.Security.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   // @Query(nativeQuery =
            //true,value = "SELECT * FROM usuarios WHERE username = ?")
    Optional<User> findByUsername(String Username);

}
