package J513;

public class User extends Person {

    private String login;

    public User (String login, String name ) {
        if (null == login || login.isBlank()) {
            throw new IllegalArgumentException("Invalid login");
        }
        //this.name = name;
        super(name);
        this.login = login;

    }
    public static void main (){
        User user = new User("LMI", "Leandro");

    }
}