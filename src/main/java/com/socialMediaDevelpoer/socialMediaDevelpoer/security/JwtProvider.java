package com.socialMediaDevelpoer.socialMediaDevelpoer.security;

import com.socialMediaDevelpoer.socialMediaDevelpoer.entity.Token;
import com.socialMediaDevelpoer.socialMediaDevelpoer.repository.TokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtProvider {
    @Autowired
    TokenRepository tokenRepository;
    // Jwt Secret
    private String jwtSecret="sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37";

    // Jwt Expiration in millis
    private Long jwtExpiration = 600000L;

    private Claims parseToken(String token) {
        // Create JwtParser
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build();
        try {
            return jwtParser.parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean validateToken(String token) {
       // System.out.println("this is old tokwn : "+token1.getToken());
       if(parseToken(token) != null){
           Token token1= tokenRepository.findByToken(token);
           if(token1.getToken()==null){
               return false;
           }
           if(token!=null && token1==null){
               return false;
           }
          return true;
       }
        return false;
    }

    //function for returning username for the token
    public String getUsernameFromToken(String token) {
        // Get claims
        Claims claims = parseToken(token);

        // Extract subject
        if(claims != null){
            return claims.getSubject();//here getSubject reflects to username
        }

        return null;
    }

    //function for generating token
    public String generateToken(String username) {
        // Create signing key
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        // Generate token
        var currentDate = new Date();
        var expiration = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }
}
