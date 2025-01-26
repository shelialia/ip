package rose.ui;

/**
 * Represents an event task that has a description, a start time, an end time, and a completion status.
 * Extends the {@link Task} class.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected String from;

    /**
     * The end time of the event.
     */
    protected String to;

    /**
     * Constructs an {@code Event} with the specified description, start time, end time, and completion status.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @param isDone      The completion status of the event.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts this event into a file format string suitable for saving.
     *
     * @return A string representing this event in file format.
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormatPrefix() + super.description + " | " + this.from + this.to;
    }

    /**
     * Returns a string representation of this event.
     *
     * @return A string representing this event, including its description, completion status,
     * start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}