package com.example.Security.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY=
            "217A25432A462D4A614E645266556A586E3272357538782F413F4428472B4B62";
    @Autowired
    private UserDetailsService userDetailsService;
    private final SignatureAlgorithm sa = SignatureAlgorithm.HS256;

    public String extractUsername(String token){
        //System.out.println("dentro de extraer Username");
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        //System.out.println("Dentro de extraer claims");
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
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 42))//Tiempo de expiracion del token = 24 horas + 1000 milisegundos
                .signWith(getSignInKey(), sa)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){

        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public boolean isTokenValid(String jwt) {
        String username;
        String token;
        if (!jwt.startsWith("Bearer" + " ")) {
            System.out.println("Falso");
            return false;
        }
        token = jwt.substring(7);
        try{
        username = extractUsername(token);}
        catch (UnsupportedJwtException e){
            System.out.println(e);
            return false;
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            //Si el mail no es nulo en la request y el mail se encuentra
            // autenticado carga en memoria al usuario para verificar token
            UserDetails userDetails =
                    this.userDetailsService.loadUserByUsername(username);
            if (isTokenValid(token, userDetails)) {
                return true;
                }
            }
            return false;
        }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }
    private boolean isSignatureValid(String token){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        //SECRET_KEY.getBytes()
        SecretKeySpec secretKeySpec =
                new SecretKeySpec(getSignInKey().getEncoded(),
                        sa.getJcaName());
        System.out.println("Header: "+header);
        System.out.println("Payload: "+payload);

        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        DefaultJwtSignatureValidator validator =
                new DefaultJwtSignatureValidator(sa, secretKeySpec);
        if (!validator.isValid(tokenWithoutSignature, signature)) {
            System.out.println("Could not verify JWT token integrity!");
            return false;
        }
        return true;
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey()).build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage()+" Could not get claims " +
                    "Token from " +
                    "passed token");
            claims = null;
        }
        return claims;
    }

    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
