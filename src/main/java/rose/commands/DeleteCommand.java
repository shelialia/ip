package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Task;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final int indexToDelete;

    public DeleteCommand(int indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

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
