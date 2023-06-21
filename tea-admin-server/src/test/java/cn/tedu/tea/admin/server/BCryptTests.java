package cn.tedu.tea.admin.server;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
public class BCryptTests {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);

    @Test
    void md5Encode() {
        String rawPassword = "123456";

        // long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            // DigestUtils.md5DigestAsHex(rawPassword.getBytes());
            String salt = UUID.randomUUID().toString();
            String encodedPassword = DigestUtils.md5DigestAsHex((salt + rawPassword).getBytes());
            System.out.println(salt + encodedPassword);
        }
        // long end = System.currentTimeMillis();
        // System.out.println(end - start);

        // 9589736e-9cc5-4fc4-985c-5ab44fd91d43b07f3e85734b8877b33ca0c092d69915
        // 59718f39-9647-4b02-9c3f-dea33e3633f98ea1dfcb56b09cce2e356125687e3f8f
        // 9d5b1ee1-38a1-4ca9-bfbd-13a919c3b7700a44e962f3ec8c87475ae289f34d8c10
    }

    @Test
    void md5Matches() {
        String rawPassword = "123456";
        String testPassword = "59718f39-9647-4b02-9c3f-dea33e3633f98ea1dfcb56b09cce2e356125687e3f8f";
        String salt = testPassword.substring(0, 36);

        String encodedPassword = DigestUtils.md5DigestAsHex((salt + rawPassword).getBytes());
        System.out.println(testPassword.equals(salt + encodedPassword));
    }

    @Test
    void bcryptEncode() {
        String rawPassword = "123456";

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            passwordEncoder.encode(rawPassword);
            // String encodedPassword = passwordEncoder.encode(rawPassword);
            // System.out.println(encodedPassword);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    void bcryptMatches() {
        String rawPassword = "123456";
        String encodedPassword = "$2a$10$N.ZOn9G6/YLFixAOPMg/h.z7pCu6v2XyFDtC4q.jeeGm/TEZyj15C";
        System.out.println(passwordEncoder.matches(rawPassword, encodedPassword));
    }
}
