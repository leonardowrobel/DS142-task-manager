package ufpr.lw.model;

public class Task extends Manageable {
    String description;
    Boolean isDone;
    Long doneAt;

    public Task(String description) {
        this.description = description;
        isDone = false;
        doneAt = null;
    }
}
