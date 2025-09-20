import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class Jep505 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Scenario: Fetching user details and their linked accounts concurrently.
        // If either subtask fails, the entire operation should fail.

        try (var scope = StructuredTaskScope.open()) { // Use ShutdownOnFailure policy
            Subtask<String> userDetailsTask = scope.fork(() -> fetchUserDetails(123L));
            Subtask<String> linkedAccountsTask = scope.fork(() -> fetchLinkedAccounts(123L));

            scope.join(); // Waits for all subtasks to complete or for a subtask to fail

            // If we reach here, both subtasks completed successfully
            String userDetails = userDetailsTask.get();
            String linkedAccounts = linkedAccountsTask.get();

            System.out.println("User Details: " + userDetails);
            System.out.println("Linked Accounts: " + linkedAccounts);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            System.err.println("Task interrupted: " + e.getMessage());
        }
    }

    private static String fetchUserDetails(Long userId) throws InterruptedException {
        // Simulate a network call or database query
        Thread.sleep(1000); // Simulate work
        if (userId == 123L) {
            return "User: John Doe, Email: john.doe@example.com";
        } else {
            throw new RuntimeException("User not found for ID: " + userId);
        }
    }

    private static String fetchLinkedAccounts(Long userId) throws InterruptedException {
        // Simulate another network call or database query
        Thread.sleep(1500); // Simulate work
        if (userId == 123L) {
            return "Accounts: Savings, Checking, Credit Card";
        } else {
            throw new RuntimeException("No linked accounts for ID: " + userId);
        }
    }
}
