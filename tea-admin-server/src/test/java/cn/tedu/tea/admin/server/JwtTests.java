package cn.tedu.tea.admin.server;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class JwtTests {

    String secretKey = "fNesMDkqrJFdsfDSwAbFLJ8SnsHJ438AF72D73aKJSmfdsafdLKKAFKDSJ";

    @Test
    void generate() {
        // 注意：即使不知道正确的Secret Key，JWT数据也是可以被解析的，所以，不要在JWT中存放敏感数据
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 997);
        claims.put("username", "XiaoPangDun");

        Date date = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
        //                                                  ↑ 注意加L，避免int溢出为负数

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

    // JWT过期的异常：io.jsonwebtoken.ExpiredJwtException
    // JWT签名错误的异常：io.jsonwebtoken.SignatureException
    // JWT格式错误的异常：io.jsonwebtoken.MalformedJwtException

    @Test
    void parse() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRob3JpdGllc0pzb25TdHJpbmciOiJbe1wiYXV0aG9yaXR5XCI6XCIvYWNjb3VudC91c2VyL2FkZC1uZXdcIn0se1wiYXV0aG9yaXR5XCI6XCIvYWNjb3VudC91c2VyL2RlbGV0ZVwifSx7XCJhdXRob3JpdHlcIjpcIi9hY2NvdW50L3VzZXIvcmVhZFwifSx7XCJhdXRob3JpdHlcIjpcIi9hY2NvdW50L3VzZXIvdXBkYXRlXCJ9LHtcImF1dGhvcml0eVwiOlwiL2NvbnRlbnQvY2F0ZWdvcnkvYWRkLW5ld1wifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L2NhdGVnb3J5L2RlbGV0ZVwifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L2NhdGVnb3J5L3JlYWRcIn0se1wiYXV0aG9yaXR5XCI6XCIvY29udGVudC9jYXRlZ29yeS91cGRhdGVcIn0se1wiYXV0aG9yaXR5XCI6XCIvY29udGVudC90YWcvYWRkLW5ld1wifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L3RhZy9kZWxldGVcIn0se1wiYXV0aG9yaXR5XCI6XCIvY29udGVudC90YWcvcmVhZFwifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L3RhZy91cGRhdGVcIn1dIiwiZXhwIjoxNjkwMjc0NTA5LCJ1c2VybmFtZSI6InJvb3QifQ.Y63XUBFTFicC0exBvfNqWckFqSbZjG3XW76HPV7KsgI";

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        String authoritiesJsonString = claims.get("authoritiesJsonString", String.class);
        System.out.println("id = " + id);
        System.out.println("username = " + username);
        System.out.println("authoritiesJsonString = " + authoritiesJsonString);

        List<SimpleGrantedAuthority> authorities
                = JSON.parseArray(authoritiesJsonString, SimpleGrantedAuthority.class);
        for (SimpleGrantedAuthority authority : authorities) {
            System.out.println(authority);
        }
    }

}
