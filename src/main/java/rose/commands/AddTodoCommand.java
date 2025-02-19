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

    /**
     * Constructs an {@code AddTodoCommand} with the specified task description.
     *
     * @param description The description of the todo task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by adding a new {@code Todo} task to the task list,
     * saving the updated task list to storage, and returning a success message.
     *
     * @param tasks The task list to which the todo task will be added.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage system to persist the task list.
     * @return A success message indicating the todo task has been added.
     * @throws RoseException If the description of the todo task is empty.
     * @throws IOException If an error occurs while saving the task list.
     */
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