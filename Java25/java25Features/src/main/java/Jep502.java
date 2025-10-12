import java.lang.StableValue;

public class Jep502 {

    public static void main(String[] args) {
        // Create a new unset StableValue
        var greeting = StableValue.<String>of();

        String message = greeting.orElseSet(() -> "Hello from StableValue!");
        System.out.println(message);
    }
}