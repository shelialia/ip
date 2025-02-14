package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Event;
import rose.tasks.Task;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;

public class UpdateEventCommand extends Command {
    private int index;
    private String newFrom;
    private String newTo;

    public UpdateEventCommand(int index, String newFrom, String newTo) {
        this.index = index;
        this.newFrom = newFrom;
        this.newTo = newTo;
    }
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        if (index < 0 || index >= tasks.getAllTasks().size()) {
            throw new RoseException("Invalid task index.");
        }
        Task task = tasks.getTask(index);
        if (!(task instanceof Event)) {
            throw new RoseException("The selected task is not an Event.");
        }

        Event event = (Event) task;

        // Preserve existing values if no update is provided
        String updatedFrom = (newFrom != null && !newFrom.isBlank()) ? newFrom : event.getFrom();
        String updatedTo = (newTo != null && !newTo.isBlank()) ? newTo : event.getTo();
        System.out.println("Extracted newFrom in parseUpdateEvent: " + newFrom);


        // Create a new Event object with updated values
        Event updatedEvent = new Event(event.getDescription(), updatedFrom, updatedTo, event.isDone());

        // Replace the old event with the updated event
        tasks.updateTask(index, updatedEvent);
        storage.save(tasks.getAllTasks());

        return ui.showSuccess("Updated event: " + updatedEvent);

    }
}
