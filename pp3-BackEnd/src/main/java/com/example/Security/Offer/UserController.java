package com.example.Security.Offer;

import com.example.Security.Entities.User;
import com.example.Security.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@RequestParam Long id){

        return userService.findById(id);

    }

}
