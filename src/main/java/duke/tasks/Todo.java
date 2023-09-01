package duke.tasks;

import java.io.Serializable;

public class Todo extends Task implements Serializable {
    private static final long serialVersionUID = 2L;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}