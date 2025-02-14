package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.TaskList;
import rose.tasks.Todo;
import rose.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a new Todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        if (description.isBlank()) {
            throw new RoseException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(description, false);
        try {
            tasks.addTask(todo);
            storage.save(tasks.getAllTasks());
            return ui.showSuccess("Added task: " + todo);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

    }
}
