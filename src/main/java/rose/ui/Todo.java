package rose.ui;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormatPrefix() + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
