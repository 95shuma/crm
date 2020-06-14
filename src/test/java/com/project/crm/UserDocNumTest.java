package com.project.crm;

import com.project.crm.backend.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootTest
public class UserDocNumTest {

    @Autowired
    private UserService userService;

    @Test
    void test_method_for_place_uniqueness2(){
        Random random = new Random();
        var userForTest = userService.getAll().get(random.nextInt(userService.getAll().size()));

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);


    }
}
