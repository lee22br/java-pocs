package org.example;


import org.junit.jupiter.api.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @BeforeAll
    static void initAll() {
        System.out.println("Start of all tests");
    }

    @BeforeEach
    void init() {
        System.out.println("Starting a new test case");
    }

    @Test
    void succeedingTest() {
        Assertions.assertTrue(1 < 2);
    }

    @Test
    @Disabled("Old Ignore - Implementation pending")
    void skippedTest() {
        System.out.println("This test won't run");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning up after test");
    }

}
