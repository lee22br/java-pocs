
//Estudar mais

public class Jep506 {

    public class UserContext {

        private static final ScopedValue<String> loggedUser = ScopedValue.newInstance();

        public static void runWithUser(String userName, Runnable action) {
            ScopedValue.where(loggedUser, userName).run(action);
        }

        public static String getLoggedUser() {
            return loggedUser.get();
        }
    }

    public static class OrderService {
        public void orderProcess() {
            // Define user scope
            UserContext.runWithUser("lee22br", this::createOrder);
        }

        private void createOrder() {
            // On anywhere, we can access user
            String user = UserContext.getLoggedUser();
            System.out.println("Order created by: " + user);

            storeDb();
        }

        private void storeDb() {
            String usuario = UserContext.getLoggedUser();
            System.out.println("Saving in DB for: " + usuario);
        }

        public static void main (){
            OrderService sp = new OrderService();
            sp.orderProcess();
        }
    }
}
