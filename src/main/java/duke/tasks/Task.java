package duke.tasks;

import java.io.Serializable;

/**
 * Represents a task with a description of what is to be done and its status.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task with a description.
     *
     * @param description A description for the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether the task has been done.
     *
     * @return True if the task has been done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns whether the task description contains a specified keyword.
     *
     * @param s The keyword to look for.
     * @return True if the description contains the specified keyword.
     */
    public boolean hasKeyword(String s) {
        return description.contains(s);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
