package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Deadline;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a new Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        if (description.isBlank() || by.isBlank()) {
            throw new RoseException("The description and deadline cannot be empty.");
        }
        Deadline deadline = new Deadline(description, by, false);
        tasks.addTask(deadline);
        storage.save(tasks.getAllTasks());
        return ui.showSuccess("Added deadline: " + deadline);
    }
}
