import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Task> tasks;
    private int id;
    private String userName;

    public List<Task> getTasks() {
        return tasks;
    }

    public User () {
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", userName'" + userName + '\''
                + '}';
    }
}
