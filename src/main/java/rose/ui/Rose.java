package rose.ui;
import rose.ui.Deadline;
import rose.ui.Event;

import java.io.IOException;
import java.util.Scanner;

public class Rose {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Rose(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (RoseException | IOException e) {
            ui.showError("Failed to load tasks. Starting fresh.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            try {
                Command command = Parser.parse(input);

                switch (command.getCommand().toLowerCase()) {
                    case "bye":
                        ui.farewell();
                        return;

                    case "list":
                        ui.showTaskList(tasks.getAllTasks());
                        break;

                    case "todo":
                        String todoDescription = command.getArguments();
                        tasks.addTask(new Todo(todoDescription, false));
                        storage.save(tasks.getAllTasks());
                        ui.showSuccess("Added task: " + todoDescription);
                        break;

                    case "deadline":
                        String[] deadlineParts = command.getArguments().split(" /by ", 2);
                        Deadline deadline_task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim(), false);
                        tasks.addTask(deadline_task);
                        storage.save(tasks.getAllTasks());
                        ui.showSuccess("Added deadline: " + deadline_task);
                        break;

                    case "event":
                        String[] eventParts = command.getArguments().split(" /from | /to ", 3);
                        Event event_task = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim(), false);
                        tasks.addTask(event_task);
                        storage.save(tasks.getAllTasks());
                        ui.showSuccess("Added event: " + event_task);
                        break;

                    case "mark":
                        int markIndex = Integer.parseInt(command.getArguments()) - 1;
                        tasks.getTask(markIndex).markAsDone();
                        storage.save(tasks.getAllTasks());
                        ui.showSuccess("Marked task as done: " + tasks.getTask(markIndex));
                        break;

                    case "unmark":
                        int unmarkIndex = Integer.parseInt(command.getArguments()) - 1;
                        tasks.getTask(unmarkIndex).markAsNotDone();
                        storage.save(tasks.getAllTasks());
                        ui.showSuccess("Marked task as not done: " + tasks.getTask(unmarkIndex));
                        break;

                    case "delete":
                        int deleteIndex = Integer.parseInt(command.getArguments()) - 1;
                        Task deletedTask = tasks.getTask(deleteIndex);
                        tasks.removeTask(deleteIndex);
                        storage.save(tasks.getAllTasks());
                        ui.showSuccess("Deleted task: " + deletedTask);
                        break;

                    default:
                        ui.showError("Unknown command!");
                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Rose("data/Rose.txt").run();
    }
}
