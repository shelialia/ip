package rose.ui;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormatPrefix() + super.description + " | " + this.from + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
