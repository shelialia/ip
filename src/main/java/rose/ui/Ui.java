package rose.ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The {@code Ui} class handles all interactions with the user.
 * It provides methods to display messages such as greetings, errors, success notifications,
 * and the list of tasks.
 */
public class Ui {

    /**
     * Constructs a new {@code Ui} object.
     */
    public Ui() {}

    /**
     * Displays the greeting message when the program starts.
     */
    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Rose");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the farewell message when the program exits.
     */
    public void farewell() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param tasks The error message to be displayed.
     */
    public void displayTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks. Try searching again.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("Error: " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to be displayed.
     */
    public void showSuccess(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks An {@link ArrayList} of {@link Task} objects representing the tasks to be displayed.
     *              If the list is empty, a message indicating no tasks is shown.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }
}