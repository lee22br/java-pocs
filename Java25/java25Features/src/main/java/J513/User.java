package J513;

public class User extends Person {

    private String login;

    public User (String login, String name ) {
        if (null == login || login.isBlank()) {
            throw new IllegalArgumentException("Invalid login");
        }
        super(name);
        this.login = login;
    }
}