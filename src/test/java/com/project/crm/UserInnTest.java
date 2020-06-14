package com.project.crm;

import com.project.crm.backend.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootTest
class UserInnTest {

    @Autowired
    private UserService userService;

    @Test
    void testMethodInnUniqueness(){
        Random random = new Random();
        var testInn = userService.getAll().get(random.nextInt(userService.getAll().size()));

        AtomicBoolean result = new AtomicBoolean(false);

        userService.getAll().stream().forEach(userInn -> {
            if (userInn.getInn().equals(testInn.getInn())){
                result.set(true);
            }
        });
        Assertions.assertEquals(true, result.get());
    }
}
