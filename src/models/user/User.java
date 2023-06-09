package models.user;

public class User {
    private int id;
    private String username;
    private String password;
    private static UserDAO userDAO = new UserDAO();

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static boolean authenticate(User user) throws Exception {
        return userDAO.authenticate(user);
    }

    public void save() throws Exception {
        userDAO.create(this);
    }
}
