package com.example.connectDB.DAO;

import com.example.connectDB.Entities.User;
import com.example.connectDB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserDao implements DAO<User>{

    @Autowired
    UserRepository repositorio;

    @Override
    public Optional<User> findById(Long id) {
        User aux=repositorio.getReferenceById(id);
        return Optional.of(aux);
    }

    @Override
    public List<User> findAll() {
            return repositorio.findAll();
    }


    @Override
    public Optional<User> save(User user){
        repositorio.save(user);
        return repositorio.findById(user.getID());
    }


    @Override
    public void delete(Long id) {
        User user=repositorio.findById(id).get();
        user.setACTIVE(0);
        repositorio.save(user);
    }

    @Override
    public boolean existsById(Long id) {
        return repositorio.existsById(id);
    }


    public Long count() {
        return repositorio.count();
    }


}
