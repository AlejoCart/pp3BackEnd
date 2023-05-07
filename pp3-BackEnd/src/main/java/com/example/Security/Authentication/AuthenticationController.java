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

    @PostMapping("/singup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        System.out.println("dentro de registrar usuario");
        //return ResponseEntity.ok(service.register(request));
        return authenticationService.register(request);

    }

    @PostMapping("/login")//Login
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);

    }

    @PostMapping("/verifies")
    public boolean verifies(@RequestBody String jwt,
                            @RequestBody String username){
        return authenticationService.isValid(jwt,username);
    }

    //isvalid
    //login
    //singup
    //verifies(String token)//devuelva objeto authenticado que sea true
    //borrar email y combertir en username
}
