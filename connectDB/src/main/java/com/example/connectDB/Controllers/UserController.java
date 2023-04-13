package com.example.connectDB.Controllers;

import com.example.connectDB.Entities.User;
import com.example.connectDB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository repositorio;

    @GetMapping("/User/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> aux;
        aux = repositorio.findById(id);
        if(aux.get().getACTIVE()){
            return aux.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/User")
    public ResponseEntity<Object> findAll() {
        List<User> lista = repositorio.findAll();
        return new ResponseEntity<Object>(lista, HttpStatus.OK);
    }

    @PostMapping("/User")
    public ResponseEntity<User> create(@RequestBody User user,
                                       @RequestHeader HttpHeaders headers) {
        if (user.getID()!=null) {
            return ResponseEntity.badRequest().build();
        } else {
            repositorio.save(user);
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("User/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        if (user.getID() == null) {
            return ResponseEntity.badRequest().build();
        }else if(!user.getACTIVE()){
            return ResponseEntity.notFound().build();
        }
        else if (repositorio.existsById(user.getID())) {
            User save = repositorio.save(user);
            return ResponseEntity.ok(save);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("User/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){

        if(id==null){
            return ResponseEntity.badRequest().build();
        }
        else if(repositorio.existsById(id)){
            repositorio.getReferenceById(id).setACTIVE(Boolean.FALSE);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/Books/")
    public ResponseEntity<User> deleteAll(){
        if(repositorio.count()>0){
            repositorio.findAll().forEach(user -> user.setACTIVE(Boolean.FALSE));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.noContent().build();
    }

}
