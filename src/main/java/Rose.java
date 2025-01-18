import java.util.ArrayList;
import java.util.Scanner;

public class Rose {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Rose");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.isEmpty()) {
                    throw new RoseException("Empty tasks are not accepted. Please try again.");
                }
                if (input.equalsIgnoreCase("bye")) {
                    // Exit message
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    System.out.println("____________________________________________________________");
                    if (taskCount == 0) {
                        System.out.println("You have not added any tasks!");
                    } else {
                        System.out.println(" Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println(" " + (i + 1) + "." + tasks.get(i));
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark ")) {
                    try {
                        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(taskNumber).markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(taskNumber));
                        System.out.println("____________________________________________________________");
                    } catch (Exception e) {
                        System.out.println(" Invalid task number!");
                    }
                } else if (input.startsWith("unmark ")) {
                    // Mark a task as not done
                    try {
                        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(taskNumber).markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(taskNumber));
                        System.out.println("____________________________________________________________");
                    } catch (Exception e) {
                        System.out.println(" Invalid task number!");
                    }
                } else if (input.startsWith("delete ")) {
                    try {
                        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                        System.out.println("____________________________________________________________");
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + tasks.get(taskNumber));
                        tasks.remove(taskNumber);
                        taskCount--;
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (Exception e) {
                        System.out.println(" Invalid task number!");
                    }
                }
                else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new RoseException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(taskCount - 1));
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("deadline ")) {
                    String[] temp = input.substring(9).split(" /by ", 2);
                    if (temp.length < 2 || temp[0].trim().isEmpty() || temp[1].trim().isEmpty()) {
                        throw new RoseException("Invalid deadline format! Use: deadline <description> /by <time>");
                    }
                    tasks.add(new Deadline(temp[0].trim(), temp[1].trim()));
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(taskCount - 1));
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event ")) {
                    String[] temp = input.substring(6).split(" /from | /to ", 3);
                    if (temp.length < 3 || temp[0].trim().isEmpty() || temp[1].trim().isEmpty() || temp[2].trim().isEmpty()) {
                        throw new RoseException("Invalid event format! Use: event <description> /from <start> /to <end>");
                    }
                    tasks.add(new Event(temp[0].trim(), temp[1].trim(), temp[2].trim()));
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks.get(taskCount - 1));
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new RoseException("Please enter a valid task.");
                }
            } catch (RoseException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
