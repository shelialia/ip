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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws RoseException, IOException {
        Command command = Parser.parse(input);
        try {
            return command.execute(tasks, ui, storage);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
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
