
import module java.base;

public class Jep511 {

    public static Map<Character, List<String>> groupByFirstLetter(String... values) {
        return Stream.of(values).collect(
                Collectors.groupingBy(s -> Character.toUpperCase(s.charAt(0))));
    }
}
