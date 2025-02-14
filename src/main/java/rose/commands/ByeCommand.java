package rose.commands;

import rose.storage.Storage;
import rose.tasks.TaskList;
import rose.ui.Ui;

/**
 * Represents a command to terminate the application.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.farewell();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
