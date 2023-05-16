package com.example.Security.Authentication;

import com.example.Security.Entities.Role;
import com.example.Security.Entities.User;
import com.example.Security.Repository.UserRepository;
import com.example.Security.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        //System.out.println("dentro de servicio registro");
        //Asume que los valores de la request no son nulos
        System.out.println(request.getUsername());
        if (mailValid(request.getUsername())) {
            var user = User.builder()
                    .id(null)
                    .name(request.getName())
                    .surname(request.getSurname())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)//meto mano
                    .active(true)
                    .img(request.getImg())
                    .career(request.getCareer())
                    .birthdate(request.getBirthdate())
                    .build();
            repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(jwtToken).build());
        } else {
            System.out.println("Mail invalido");
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        //System.out.println("Usuario encontrador por nombre: "+repository
        // .findByUsername(username));
        try {
            authenticationManager.authenticate(//Gestiona automaticamente que el
                    // usuario y contraseña esten correctos
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
        } catch (
                AuthenticationException e) {
            return ResponseEntity.notFound().build();
        }

        if (!(repository.findByUsername(request.getUsername()).get().isActive()))
            return ResponseEntity.notFound().build();
        //Usuario autenticado
        var user =
                repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        System.out.println("Usuario autenticado con exito");
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(jwtToken).build());
    }

    private boolean mailValid(String email) {
        /*String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";*/
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "itbeltran.com.ar";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;

        /*if(pat.matcher(email).matches()){ Ignorar b_b
            byte aux= (byte) email.indexOf("@");
            String domain=email.substring(aux);
            if(!(domain.equals("itbeltran.com.ar"))) return false;
        }*/
        return pat.matcher(email).matches();
    }

    public boolean isValid(String jwt) {
        //System.out.println("Entrado al servicio de autenticacion");
        return jwtService.isTokenValid(jwt);
    }
}

