package com.example.Security.Service;

import com.example.Security.Authentication.AuthenticationRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY=
            "217A25432A462D4A614E645266556A586E3272357538782F413F4428472B4B62";
    private UserDetailsService userDetailsService;

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims= extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){

        /*return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24))//Tiempo de expiracion del token = 24 horas + 1000 milisegundos
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();*/
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails){

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24))//Tiempo de expiracion del token = 24 horas + 1000 milisegundos
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){

        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

   /* public boolean isTokenValid(String token, String usernameRequest){

        final String username=extractUsername(token);
        return (username.equals(usernameRequest)) && !isTokenExpired(token);
    }*/
    public boolean isTokenValid(String jwt) {
        String username;
        String token;
        if (!jwt.startsWith("Bearer" + " ")) {
            return false;
        }
        System.out.println("Token sin cortar: "+jwt);
        token = jwt.substring(7);
        System.out.println("Token cortado: "+token);
        username = extractUsername(jwt);
        System.out.println("Username del token cortado: "+username);
        if (username != null /*&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()*/) {
            //Si el mail no es nulo en la request y el mail se encuentra
            // autenticado carga en memoria al usuario para verificar token
            UserDetails userDetails =
                    this.userDetailsService.loadUserByUsername(username);
            if (isTokenValid(jwt, userDetails)) {
                return true;
                }
            }
            return false;
        }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJwt(token)
                .getBody();

    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
