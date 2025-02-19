package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Task;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword to search for.
     *
     * @param keyword The keyword used to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks that contain the specified keyword,
     * and displaying the matching tasks to the user.
     *
     * @param tasks   The task list in which to search for matching tasks.
     * @param ui      The user interface for displaying search results.
     * @param storage The storage system (not used in this command).
     * @return A message containing the list of matching tasks.
     * @throws RoseException If an error occurs while searching for tasks.
     * @throws IOException   If an error occurs during execution (though not expected in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.displayTasks(matchingTasks);
    }
}
