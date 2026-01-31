package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testSupplier() {
        Supplier<Double> randomSupplier = () -> new Random().nextDouble();

        Double value = randomSupplier.get();

        assertNotNull(value);
        assertTrue(value >= 0.0 && value < 1.0);
    }

    @Test
    void testConsumer() {
        List<String> logs = new ArrayList<>();

        Consumer<String> logger = message -> logs.add("LOG: " + message);

        logger.accept("Application OK");
        logger.accept("Application Fail");

        assertEquals(2, logs.size());
        assertEquals("LOG: Application OK", logs.get(0));
    }

    @Test
    void testUnaryOperator() {
        UnaryOperator<String> sanitize = text -> text.trim().toUpperCase();

        String input = "  java streams  ";
        String result = sanitize.apply(input);

        assertEquals("JAVA STREAMS", result);
    }

    @Test
    void testBinaryOperator() {
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        BinaryOperator<Integer> max = Integer::max;

        assertEquals(15, sum.apply(10, 5));
        assertEquals(50, max.apply(10, 50));
    }

    @Test
    void testFunctionalInterfacesInStreams() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Integer totalSum = numbers.stream()
                .reduce(0, (a, b) -> a + b);

        assertEquals(15, totalSum);
    }

    @Test
    void testProcessingPipeline() {
        UnaryOperator<String> trim = String::trim;
        UnaryOperator<String> toLower = String::toLowerCase;
        UnaryOperator<String> censor = text -> text.replace("fuck", "****");
        UnaryOperator<String> addTag = text -> "[PROCESSED] " + text;

        Function<String, String> pipeline = trim
                .andThen(toLower)
                .andThen(censor)
                .andThen(addTag);

        String input = "  This is a Fuck message  ";
        String result = pipeline.apply(input);

        assertEquals("[PROCESSED] this is a **** message", result);
    }

    @Test
    void testConsumerChaining() {
        StringBuilder report = new StringBuilder();

        Consumer<String> addToReport = report::append;
        Consumer<String> printToConsole = System.out::println;

        Consumer<String> combinedConsumer = addToReport.andThen(printToConsole);

        combinedConsumer.accept("Data Entry 1; ");
        combinedConsumer.accept("Data Entry 2; ");

        assertTrue(report.toString().contains("Data Entry 1"));
    }
}