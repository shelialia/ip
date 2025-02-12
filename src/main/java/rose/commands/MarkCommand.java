package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Task;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        if (index < 0 || index >= tasks.getAllTasks().size()) {
            throw new RoseException("Invalid task index.");
        }
        Task task = tasks.getTask(index);
        task.markAsDone();
        try {
            storage.save(tasks.getAllTasks());
            return ui.showSuccess("Marked task as done: " + task);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
