package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

/**
 * Represents a command parsed from user input.
 * Each specific command type should be implemented as a subclass that defines its execution logic.
 */
public abstract class Command {

    /**
     * Executes the command with the provided components.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage system for saving tasks persistently.
     * @return The response message generated after executing the command.
     * @throws RoseException If an error related to command execution occurs.
     * @throws IOException   If an I/O error occurs while accessing storage.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException;

    /**
     * Determines whether this command should terminate the program.
     *
     * @return {@code true} if this command signals an exit, otherwise {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
