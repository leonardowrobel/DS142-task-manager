package ufpr.lw.model;

import java.util.List;

public class User extends Manageable {
    private String username;
    private String password;
    private List<Task> tasks;
    private boolean isActive;

    public User() {
        super();
        this.isActive = false;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isActive = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFisrtName(String fisrtName) {
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
