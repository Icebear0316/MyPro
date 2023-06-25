package cn.tedu.tea.admin.server;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    String secretKey = "fNesMDkqrJFdsfDSwAbFLJ8SnsHJ438AF72D73aKJSmfdsafdLKKAFKDSJ";

    @Test
    void generate() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 997);
        claims.put("username", "XiaoPangDun");

        Date date = new Date(System.currentTimeMillis() + 3 * 60 * 1000);

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                .setExpiration(date)
                // Verify Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // Done
                .compact();
        System.out.println(jwt);
    }

    // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTk3LCJleHAiOjE2ODc2NzUyNjYsInVzZXJuYW1lIjoiWGlhb1BhbmdEdW4ifQ.VxYYbbRYGaaetK3AipiPpkeyfOZBFZdyQVbhGMSFgCA

    @Test
    void parse() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTk3LCJleHAiOjE2ODc2Nzc1NjUsInVzZXJuYW1lIjoiWGlhb1BhbmdEdW4ifQ.t6TwS1Sm7AUA418OJSnuOdPmXLZ43TU0KyQt_WJ2nvk";

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Object id = claims.get("id");
        Object username = claims.get("username");
        System.out.println("id = " + id);
        System.out.println("username = " + username);
    }

}
