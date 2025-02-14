package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Event;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a new Event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        if (description.isBlank() || from.isBlank() || to.isBlank()) {
            throw new RoseException("The event description, start, and end time cannot be empty.");
        }
        Event event = new Event(description, from, to, false);
        tasks.addTask(event);
        storage.save(tasks.getAllTasks());
        return ui.showSuccess("Added event: " + event);
    }
}
