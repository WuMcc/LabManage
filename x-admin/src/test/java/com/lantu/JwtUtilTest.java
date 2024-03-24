package com.lantu;

import com.lantu.common.utils.JwtUtil;
import com.lantu.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPhone("12399988877");
        String token = jwtUtil.createToken(user);
        System.out.println(token);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjZTUxNWQ3ZS01Y2RjLTQxZmEtOGNlYy03ODM4MjkxYWUzNjEiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTcwOTY0OTQwOSwiZXhwIjoxNzA5NjUxMjA5fQ.nvu1pvhMv2KeDDzq8ewdRwkKK0T3sSvr9upKn54nV-8";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void testParseJwt2(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjZTUxNWQ3ZS01Y2RjLTQxZmEtOGNlYy03ODM4MjkxYWUzNjEiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiMTIzOTk5ODg4NzdcIixcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTcwOTY0OTQwOSwiZXhwIjoxNzA5NjUxMjA5fQ.nvu1pvhMv2KeDDzq8ewdRwkKK0T3sSvr9upKn54nV-8";
        User user = jwtUtil.parseToken(token,User.class);
        System.out.println(user);
    }
}
