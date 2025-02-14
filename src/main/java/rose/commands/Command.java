package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

/**
 * Represents a command parsed from user input.
 * Each command type should have its own subclass that implements execution logic.
 */
public abstract class Command {
    /**
     * Executes the command with the given components.
     *
     * @param tasks   The task list the command operates on.
     * @param ui      The UI for user interaction.
     * @param storage The storage for persistent task saving.
     * @return The response message after executing the command.
     * @throws RoseException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException;

    /**
     * Indicates whether the command should terminate the program.
     *
     * @return {@code true} if the command is an exit command, otherwise {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
