package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

import static org.example.FunctionUtil.listCombiner;
import static org.junit.jupiter.api.Assertions.*;


public class AppTest 
{

    @Test
    public void testAdd(){
        MathOperation addition = (a,b) -> a + b;

        assertEquals(2, addition.execute(1,1));
    }

    @Test
    public void testMultiply(){
        MathOperation multiplication = (a,b) -> a * b;

        assertEquals(4, multiplication.execute(2,2));
    }

    @Test
    public void testPow(){
        MathOperation multiplication = Math::pow;

        assertEquals(16, multiplication.execute(2,4));
    }


    @Test void testBifunctional(){
        List<String> list1 = Arrays.asList("a", "b", "c");
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        List<String> list3 = Arrays.asList("a1", "b2", "c3");

        List<String> result = listCombiner(list1, list2, (letter, number) -> letter + number);

        assertTrue(result.containsAll(list3));
    }

    @Test
    void testLoginValidatorWithBiPredicate() {
        BiPredicate<String, String> loginValidator = (user, pass) ->
                "admin".equals(user) && "1234".equals(pass);

        assertTrue(loginValidator.test("admin", "1234"));
        assertFalse(loginValidator.test("admin", "wrong_pass"));
        assertFalse(loginValidator.test("guest", "1234"));
    }

    @Test
    void testNameFormatterWithBiFunction() {
        BiFunction<String, String, String> formatter = (name, role) ->
                String.format("%s (%s)", name.toUpperCase(), role);

        String result = formatter.apply("Alice", "Manager");

        assertEquals("ALICE (Manager)", result);
    }


}
