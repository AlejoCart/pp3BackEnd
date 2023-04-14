package com.example.connectDB.Controllers;

import com.example.connectDB.Entities.User;
import com.example.connectDB.Repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
@RestController
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserRepository repositorio;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("api/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> aux;
        aux = repositorio.findById(id);
        if(aux.get().getACTIVE()==1){
            return aux.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/users")
    public ResponseEntity<Object> findAll() {
        List<User> lista=new ArrayList<>();
        /*repositorio.findAll().forEach(user-> {
            if(user.getACTIVE()==1)
                lista.add(user);
        });*/
        repositorio.findAll().forEach(user -> {
            if (user.getACTIVE() == 1) lista.add(user);
        });
        return new ResponseEntity<Object>(lista, HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> create(@RequestBody User user,
                                       @RequestHeader HttpHeaders headers) {
        //System.out.println(user.toString());
        if(user.getID()!=null ) {
            return ResponseEntity.badRequest().build();
        } else {
            User aux= user;
            aux.setPASSWORD(passwordEncoder.encode(user.getPASSWORD()));
            //No se porque la DB no me hace caso al default 1 para el active,
            // si podes hacer que funcino desde la DB borra la linea de abajo
            // Cambia tambien linea 103, adentro del update
            aux.setACTIVE(1);
            repositorio.save(aux);
            return ResponseEntity.ok(aux);
        }
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                       @RequestBody Object userAux) {

        ObjectMapper obj=new ObjectMapper();
        User user = null;
        String jsonStr = null;
        String passAux="";

        //Casteo a JSON necesario porque sino se rompe al no poder castear el
        // LinkedHashMap que recibe a la entidad usuario
        try {
            jsonStr = obj.writeValueAsString(userAux);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        // Displaying Java object into a JSON string
        System.out.println(jsonStr);

        try {
            user = obj.readValue(jsonStr, User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("usuario despues de casteo de Json a clase: "+user.toString());
        //User user=(User)userAux;
        if (id== null) {
            return ResponseEntity.badRequest().build();
        } else if (repositorio.existsById(id) && repositorio.getReferenceById(id).getACTIVE()==1) {
            user.setID(id);
            passAux=user.getPASSWORD();
            user.setPASSWORD(passwordEncoder.encode(passAux));
            //Hotfix porque la base de datos no me deja ponerlo por default
            user.setACTIVE(1);
            repositorio.save(user);
            return ResponseEntity.ok(repositorio.getReferenceById(user.getID()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){

        if(id==null){
            return ResponseEntity.badRequest().build();
        }
        else if(repositorio.existsById(id) && repositorio.getReferenceById(id).getACTIVE()==1){
            User aux=repositorio.getReferenceById(id);
            aux.setACTIVE(0);
            repositorio.save(aux);
            System.out.println("Delete - Active of: "+repositorio.getReferenceById(id).getNAME()+" "+repositorio.getReferenceById(id).getACTIVE());
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/Users/")
    public ResponseEntity<User> deleteAll(){
        if(repositorio.count()>0){
            repositorio.findAll().forEach(user -> user.setACTIVE(0));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.noContent().build();
    }

}
