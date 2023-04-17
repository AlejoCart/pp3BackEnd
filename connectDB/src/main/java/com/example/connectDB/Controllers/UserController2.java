package com.example.connectDB.Controllers;

import com.example.connectDB.DAO.UserDao;
import com.example.connectDB.Entities.User;
import com.example.connectDB.Repositories.UserRepository;
import com.example.connectDB.Services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@CrossOrigin(origins = "http://localhost:4200")
public class UserController2 {
    @Autowired
    private UserService servicio;

    @GetMapping("api/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return servicio.findById(id);
    }

    @GetMapping("/api/users")
    public ResponseEntity<Object> findAll() {
        return servicio.findAll();
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> create(@RequestBody User user,
                                       @RequestHeader HttpHeaders headers) {
       return servicio.create(user);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                       @RequestBody Object userAux) {
        return servicio.update(id,userAux);
    }
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        return servicio.deleteById(id);
    }

    @DeleteMapping("/api/Users/")
    public ResponseEntity<User> deleteAll(){
        return servicio.deleteAll();
    }

}
