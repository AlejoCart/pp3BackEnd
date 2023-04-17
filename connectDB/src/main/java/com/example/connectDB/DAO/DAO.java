package com.example.connectDB.DAO;

import com.example.connectDB.Entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface DAO<T> {


    Optional<User> findById(Long id);
    List<T> findAll();
    Optional<User>save(User user);

    void delete(Long id);

    boolean existsById(Long id);



}
