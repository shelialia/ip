package rose.commands;

import rose.storage.Storage;
import rose.tasks.TaskList;
import rose.ui.Ui;

/**
 * Represents a command to terminate the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command by returning a farewell message to the user.
     *
     * @param tasks The task list (not used in this command).
     * @param ui The user interface to display messages to the user.
     * @param storage The storage system (not used in this command).
     * @return A farewell message from the UI.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.farewell();
    }

    /**
     * Indicates that this command will terminate the application.
     *
     * @return {@code true} since this command signals an exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}