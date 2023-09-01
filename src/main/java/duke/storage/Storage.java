package duke.storage;

import duke.DukeException;
import duke.TaskList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    private final Path path;

    public Storage(String path) {
        this.path = Path.of(path);
    }

    public TaskList load() throws DukeException {
        if (Files.notExists(path)) {
            throw new DukeException("No existing tasks found");
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(path.toString());
            ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);

            TaskList tasks = (TaskList) objInputStream.readObject();

            objInputStream.close();
            fileInputStream.close();
            return tasks;
        } catch (IOException | ClassNotFoundException e) {
            try {
                Files.delete(path);
            } catch (IOException ignored) {
            }
            throw new DukeException(String.format("Something went wrong loading existing tasks from %s", path));
        }
    }

    public void save(TaskList tasks) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path.toString());
            ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(tasks);

            objOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
    }
}