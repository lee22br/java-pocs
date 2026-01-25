package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.FunctionUtil.listCombiner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

}
