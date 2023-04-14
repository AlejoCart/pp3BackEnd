package com.example.connectDB.Controllers;

import com.example.connectDB.Entities.User;
import com.example.connectDB.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    UserRepository repositorio;

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
        System.out.println(user.toString());
        if(user.getID()!=null ) {
            return ResponseEntity.badRequest().build();
        } else {
            repositorio.save(user);
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                       @RequestBody Object userAux) {
        User user=(User)userAux;
        if (id== null) {
            return ResponseEntity.badRequest().build();
        } else if (repositorio.existsById(id) && repositorio.getReferenceById(id).getACTIVE()==1) {
            System.out.println("Usuario entrante: "+user.toString());
            System.out.println("ID de user entrante: "+user.getID());
            user.setID(id);
            System.out.println("ID corregida: "+user.getID());
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
