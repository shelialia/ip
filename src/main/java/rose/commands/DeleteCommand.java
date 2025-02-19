package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Task;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int indexToDelete;

    /**
     * Constructs a {@code DeleteCommand} with the specified task index to delete.
     *
     * @param indexToDelete The index of the task to be deleted.
     */
    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    /**
     * Executes the command by removing a task from the task list,
     * updating storage, and returning a success message.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving tasks persistently.
     * @return A success message indicating the deleted task.
     * @throws RoseException If the task index is invalid.
     * @throws IOException   If an error occurs while saving the updated task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        if (indexToDelete < 0 || indexToDelete >= tasks.getAllTasks().size()) {
            throw new RoseException("Invalid task index.");
        }
        Task deletedTask = tasks.getTask(indexToDelete);
        tasks.removeTask(indexToDelete);
        try {
            storage.save(tasks.getAllTasks());
            return ui.showSuccess("Deleted task: " + deletedTask);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
