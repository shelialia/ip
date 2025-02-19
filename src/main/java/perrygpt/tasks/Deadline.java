package perrygpt.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. The task is to be completed by the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Constructor for a Deadline Object
     *
     * @param description description of task
     * @param by deadline of task
     * @param isDone tracks whether the task is complete
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    /**
     * Returns a formatted string containing the deadline information for storing in the Storage object
     * @return formatted string
     */
    @Override
    public String toFileFormat() {
        return String.format("D" + super.toFileFormatPrefix() + super.description + " | "
                + this.by.format(INPUT_FORMAT));
    }

    /**
     * Returns the string representation of a deadline
     * @return a string of a deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + this.by.format(OUTPUT_FORMAT) + ")";
    }
}
