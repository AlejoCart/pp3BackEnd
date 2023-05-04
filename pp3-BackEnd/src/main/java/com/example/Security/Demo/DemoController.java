package com.example.Security.Demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-Controller")
public class DemoController {

    @GetMapping("/Hola")
    ResponseEntity<String> Hola(){
        return ResponseEntity.ok("Saludos desde un endpoint seguro");
    }

}
