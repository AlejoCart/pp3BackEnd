package com.example.connectDB.Services;
import com.example.connectDB.DAO.UserDao;


import com.example.connectDB.Entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserDao UserDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<User> findById(Long id){
        Optional<User> aux;
        aux = UserDao.findById(id);
        if(aux.get().getACTIVE()==1){
            return aux.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Object> findAll(){
        List<User> lista=new ArrayList<>();
        UserDao.findAll().forEach(user -> {
            if (user.getACTIVE() == 1) lista.add(user);
        });
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    public ResponseEntity<User> create(User user) {
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
            UserDao.save(aux);
            return ResponseEntity.ok(aux);
        }
    }
    public ResponseEntity<User> update(Long id, Object userAux) {

        ObjectMapper obj=new ObjectMapper();
        com.example.connectDB.Entities.User user = null;
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
            user = obj.readValue(jsonStr, com.example.connectDB.Entities.User.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("usuario despues de casteo de Json a clase: "+user.toString());
        //User user=(User)userAux;
        if (id== null) {
            return ResponseEntity.badRequest().build();
        } else if (UserDao.existsById(id) && UserDao.findById(id).get().getACTIVE()==1) {
            user.setID(id);
            passAux=user.getPASSWORD();
            user.setPASSWORD(passwordEncoder.encode(passAux));
            //Hotfix porque la base de datos no me deja ponerlo por default
            user.setACTIVE(1);
            UserDao.save(user);
            return ResponseEntity.ok(UserDao.findById(user.getID()).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<User> deleteById(Long id){
        if(id==null){
            return ResponseEntity.badRequest().build();
        }
        else if(UserDao.existsById(id) && UserDao.findById(id).get().getACTIVE()==1){
            User aux=UserDao.findById(id).get();
            aux.setACTIVE(0);
            UserDao.save(aux);
            System.out.println("Delete - Active of: "+UserDao.findById(id).get().getNAME()+" "+UserDao.findById(id).get().getACTIVE());
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> deleteAll(){
        if(UserDao.count()>0){
            UserDao.findAll().forEach(user -> user.setACTIVE(0));
            return ResponseEntity.noContent().build();
        }
        else return ResponseEntity.noContent().build();
    }

}
