package org.example;


import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit test for simple App.
 */
public class AppTest {


    @Test
    void testArrayToStream() {
        Integer[] numArray = {10, 20, 30, 40};

        int sum = Arrays.stream(numArray)
                .mapToInt(Integer::intValue)
                .sum();

        assertEquals(100, sum);
    }

    @Test
    void testMapTransformation() {
        List<String> numbers = Arrays.asList("1", "2", "3");

        List<Integer> squares = numbers.stream()
                .map(Integer::parseInt)
                .map(n -> n * n)
                .collect(Collectors.toList());

        List<Integer> expected = Arrays.asList(1, 4, 9);
        assertEquals(expected, squares);
    }

    @Test
    void testFilterAndPredicates() {
        List<String> names = Arrays.asList("Joao", "Maria", "Jose", "Daniel", "Eduardo", "Carlos");

        Predicate<String> startsWithCD = name -> name.startsWith("C") || name.startsWith("D");
        List<String> filtered = names.stream()
                .filter(startsWithCD)
                .collect(Collectors.toList());

        assertEquals(2, filtered.size());
        assertTrue(filtered.contains("Carlos"));
        assertFalse(filtered.contains("Joao"));
    }

    @Test
    void testListToMapConversion() {
        List<String> fruitList = Arrays.asList("Apple", "Banana", "Orange");

        Map<String, Integer> fruitMap = fruitList.stream()
                .collect(Collectors.toMap(
                        fruit -> fruit,
                        String::length
                ));

        assertEquals(5, fruitMap.get("Apple"));
        assertEquals(6, fruitMap.get("Banana"));
        assertEquals(3, fruitMap.size());
    }

    @Test
    void testStreamPipelineWithFindAny() {
        List<Integer> values = Arrays.asList(5, 12, 8, 18, 20);

        Optional<Integer> firstMatch = values.stream()
                .filter(n -> n > 10)
                .findFirst();

        assertTrue(firstMatch.isPresent());
        assertEquals(12, firstMatch.get());
    }

    @Test
    void testStreamSkip() {
        List<Integer> values = Arrays.asList(5, 7, 15, 22, 24, 35, 44, 48, 60);

        List<Integer> result = values.stream()
                .skip(2)
                .collect(Collectors.toList());

        assertEquals(7, result.size());
        assertFalse(result.contains(5));
        assertFalse(result.contains(7));

        result = values.stream()
                .limit(7)
                .collect(Collectors.toList());

        assertEquals(7, result.size());
        assertFalse(result.contains(48));
        assertFalse(result.contains(60));
    }

    @Test
    void testFlatMapForNestedLists() {

        List<List<String>> teamMembers = Arrays.asList(
                Arrays.asList("Joao", "Aline", "Jose", "Daniel", "Eduardo", "Carlos"),
                Arrays.asList("Aline", "Jose"),
                Arrays.asList("Daniel", "Eve")
        );

        List<String> distinctMembers = teamMembers.stream()
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(7, distinctMembers.size());
        assertEquals("Aline", distinctMembers.get(0));
        assertNotEquals("Aline", distinctMembers.get(1));
        assertEquals("Eve", distinctMembers.get(4));
    }

    @Test
    void testGroupingByLogic() {
        List<Person> personList = Arrays.asList(
                new Person("Aline", 12),
                new Person("Daniel", 12),
                new Person("Joao", 13),
                new Person("Maria", 13)
        );

        // Grouping employees by their department
        Map<Integer, List<Person>> byDept = personList.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        assertEquals(2, byDept.get(12).size());
        assertEquals(2, byDept.get(13).size());
    }
}
