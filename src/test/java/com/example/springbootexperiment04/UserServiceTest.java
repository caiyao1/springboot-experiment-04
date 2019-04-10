package com.example.springbootexperiment04;

import com.example.springbootexperiment04.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void buyCarTest() {
        userService.buyCar();
    }

    @Test
    public void addUserTest() {
        userService.addUser();
    }
}