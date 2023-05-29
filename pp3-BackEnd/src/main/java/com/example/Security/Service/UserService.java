package com.example.Security.Service;

import com.example.Security.Entities.User;
import com.example.Security.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    public ResponseEntity<User> findById(Long id) {
        if(id<0 || id> userRepository.count())
            return ResponseEntity.badRequest().build();
        var aux=userRepository.findById(id);
        return ResponseEntity.of(aux);
    }


}
