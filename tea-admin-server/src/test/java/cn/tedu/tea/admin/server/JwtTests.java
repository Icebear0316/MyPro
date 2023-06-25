package cn.tedu.tea.admin.server;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    @Test
    void generate() {
        String secretKey = "fNesMDkqrJFdsfDSwAbFLJ8SnsHJ438AF72D73aKJSmfdsafdLKKAFKDSJ";

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
        String jwt = "";
    }

}
