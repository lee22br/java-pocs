package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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

}
