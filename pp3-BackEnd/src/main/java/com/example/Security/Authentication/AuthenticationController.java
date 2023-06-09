package com.example.Security.Authentication;

import com.example.Security.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        System.out.println("dentro de registrar usuario");
        //return ResponseEntity.ok(service.register(request));
        return authenticationService.register(request);

    }

    @PostMapping("/login")//Login
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("Login controller");
        return authenticationService.authenticate(request);

    }

    @PostMapping("/verifies")
    public boolean verifies(@RequestBody String jwt){
        //System.out.println("Entrado al endpoint verifies");
        return authenticationService.isValid(jwt);
    }

    //isvalid
    //login
    //singup
    //verifies(String token)//devuelva objeto authenticado que sea true
    //borrar email y combertir en username
}
