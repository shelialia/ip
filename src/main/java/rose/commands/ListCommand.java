package rose.commands;

import rose.storage.Storage;
import rose.tasks.TaskList;
import rose.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks.getAllTasks());
    }
}
