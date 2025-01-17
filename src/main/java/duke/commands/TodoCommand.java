package duke.commands;

import java.util.regex.Pattern;

import duke.tasks.TaskException;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * A command to add a new todo.
 */
public class TodoCommand extends Command {
    private static final Pattern pattern = Pattern.compile("^todo\\s+(?<description>.+)$");

    public TodoCommand(String s) throws CommandException {
        super(s, pattern);
    }

    @Override
    protected String getInvalidFormatMessage() {
        return String.join("\n", "Invalid format for command `todo`!", "Usage: todo <DESCRIPTION>");
    }

    @Override
    public CommandResult run(TaskList tasks) throws CommandException {
        String description = matcher.group("description");

        if (description.isEmpty()) {
            throw new CommandException("Todo description cannot be empty!");
        }

        Todo todo = new Todo(description);
        try {
            tasks.add(todo);

            return new CommandResult(
                    true,
                    "Got it. I've added this task:",
                    todo.toString(),
                    String.format(
                            "Now you have %d %s in the list.",
                            tasks.size(),
                            tasks.size() == 1 ? "task" : "tasks"
                    )
            );
        } catch (TaskException e) {
            throw new CommandException("A similar todo already exists!");
        }
    }
}
