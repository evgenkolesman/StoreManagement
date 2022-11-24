package ru.kolesnikov.storemanagement.controller;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration(initializers = {
//        TestContainersInitializer.Initializer.class
//})
public abstract class TestAbstractIntegration {

//    @BeforeAll
//    static void init() {
//        TestContainersInitializer.postgresDBContainer.start();
//        TestContainersInitializer.kafka.start();
//        TestContainersInitializer.mongoDBContainer.start();
//    }

}
