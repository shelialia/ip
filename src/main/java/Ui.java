import java.util.ArrayList;

public class Ui {
    public Ui() {}

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Rose");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void farewell() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println("Error: " + message);
        System.out.println("____________________________________________________________");
    }

    public void showSuccess(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

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