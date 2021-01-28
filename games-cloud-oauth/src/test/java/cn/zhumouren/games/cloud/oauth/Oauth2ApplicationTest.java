package cn.zhumouren.games.cloud.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Oauth2ApplicationTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void getPassword(){
        System.out.println(passwordEncoder.encode("123456"));
    }
}
