package rose;
import rose.commands.Command;
import rose.parser.Parser;
import rose.tasks.*;
import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Task;
import rose.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Rose} class represents the main application for managing tasks.
 * It handles user input, processes commands, and interacts with other classes like {@link TaskList},
 * {@link Storage}, {@link Ui}, and {@link Parser}.
 */
public class Rose {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new {@code Rose} application.
     * Initializes the storage, task list, and user interface.
     * Loads tasks from the specified file, or starts fresh if the file cannot be loaded.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Rose(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (RoseException | IOException e) {
            ui.showError("Failed to load tasks." + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop for the {@code Rose} application.
     * Handles user input, processes commands, and interacts with the {@link TaskList}.
     */
    public void run() {
        ui.greet();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            try {
//                Command command = Parser.parse(input);
//
//                switch (command.getCommand().toLowerCase()) {
//                    case "bye":
//                        ui.farewell();
//                        return;
//
//                    case "list":
//                        ui.showTaskList(tasks.getAllTasks());
//                        break;
//
//                    case "find":
//                        ArrayList<Task> matchingTasks = tasks.findTasks(command.getArguments());
//                        ui.displayTasks(matchingTasks);
//                        break;
//
//                    case "todo":
//                        String todoDescription = command.getArguments();
//                        tasks.addTask(new Todo(todoDescription, false));
//                        storage.save(tasks.getAllTasks());
//                        ui.showSuccess("Added task: " + todoDescription);
//                        break;
//
//                    case "deadline":
//                        String[] deadlineParts = command.getArguments().split(" /by ", 2);
//                        Deadline deadline_task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim(), false);
//                        tasks.addTask(deadline_task);
//                        storage.save(tasks.getAllTasks());
//                        ui.showSuccess("Added deadline: " + deadline_task);
//                        break;
//
//                    case "event":
//                        String[] eventParts = command.getArguments().split(" /from | /to ", 3);
//                        Event event_task = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim(), false);
//                        tasks.addTask(event_task);
//                        storage.save(tasks.getAllTasks());
//                        ui.showSuccess("Added event: " + event_task);
//                        break;
//
//                    case "mark":
//                        int markIndex = Integer.parseInt(command.getArguments()) - 1;
//                        tasks.getTask(markIndex).markAsDone();
//                        storage.save(tasks.getAllTasks());
//                        ui.showSuccess("Marked task as done: " + tasks.getTask(markIndex));
//                        break;
//
//                    case "unmark":
//                        int unmarkIndex = Integer.parseInt(command.getArguments()) - 1;
//                        tasks.getTask(unmarkIndex).markAsNotDone();
//                        storage.save(tasks.getAllTasks());
//                        ui.showSuccess("Marked task as not done: " + tasks.getTask(unmarkIndex));
//                        break;
//
//                    case "delete":
//                        int deleteIndex = Integer.parseInt(command.getArguments()) - 1;
//                        Task deletedTask = tasks.getTask(deleteIndex);
//                        tasks.removeTask(deleteIndex);
//                        storage.save(tasks.getAllTasks());
//                        ui.showSuccess("Deleted task: " + deletedTask);
//                        break;
//
//                    default:
//                        ui.showError("Unknown command!");
//                }
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws RoseException {
        try {
            Command command = Parser.parse(input);

            switch (command.getCommand().toLowerCase()) {
                case "bye":
                    return ui.farewell();

                case "list":
                    return ui.showTaskList(tasks.getAllTasks());

                case "find":
                    ArrayList<Task> matchingTasks = tasks.findTasks(command.getArguments());
                    return ui.displayTasks(matchingTasks);

                case "todo":
                    String todoDescription = command.getArguments();
                    tasks.addTask(new Todo(todoDescription, false));
                    storage.save(tasks.getAllTasks());
                    return ui.showSuccess("Added task: " + todoDescription);

                case "deadline":
                    String[] deadlineParts = command.getArguments().split(" /by ", 2);
                    Deadline deadline_task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim(), false);
                    tasks.addTask(deadline_task);
                    storage.save(tasks.getAllTasks());
                    return ui.showSuccess("Added deadline: " + deadline_task);

                case "event":
                    String[] eventParts = command.getArguments().split(" /from | /to ", 3);
                    Event event_task = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim(), false);
                    tasks.addTask(event_task);
                    storage.save(tasks.getAllTasks());
                    return ui.showSuccess("Added event: " + event_task);

                case "mark":
                    int markIndex = Integer.parseInt(command.getArguments()) - 1;
                    tasks.getTask(markIndex).markAsDone();
                    storage.save(tasks.getAllTasks());
                    return ui.showSuccess("Marked task as done: " + tasks.getTask(markIndex));

                case "unmark":
                    int unmarkIndex = Integer.parseInt(command.getArguments()) - 1;
                    tasks.getTask(unmarkIndex).markAsNotDone();
                    storage.save(tasks.getAllTasks());
                    return ui.showSuccess("Marked task as not done: " + tasks.getTask(unmarkIndex));

                case "delete":
                    int deleteIndex = Integer.parseInt(command.getArguments()) - 1;
                    Task deletedTask = tasks.getTask(deleteIndex);
                    tasks.removeTask(deleteIndex);
                    storage.save(tasks.getAllTasks());
                    return ui.showSuccess("Deleted task: " + deletedTask);

                default:
                    return ui.showError("Unknown command!");
            }
        } catch (Exception e) {
            throw new RoseException(e.getMessage());
        }
    }

    /**
     * The main entry point for the {@code Rose} application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Rose("data/Rose.txt").run();
    }
}
