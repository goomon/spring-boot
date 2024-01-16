package com.github.goomon.boot.trace.app.aspect;

import com.github.goomon.boot.app.SimpleRepository;
import com.github.goomon.boot.app.SimpleService;
import com.github.goomon.boot.aspect.Aspect1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(Aspect1.class)
public class AopTest {

    @Autowired
    SimpleService service;

    @Autowired
    SimpleRepository simpleRepository;

    @Test
    void test() {
        service.save("hihi");
    }
}
