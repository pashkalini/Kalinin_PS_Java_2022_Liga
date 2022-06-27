package base;

import base.Task;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public User() {
        this.tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "base.User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}