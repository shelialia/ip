package rose.ui;

import rose.ui.TaskType;

import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType tasktype;

    public Task(String description, TaskType tasktype, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tasktype = tasktype;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String toFileFormatPrefix() {
        return isDone? " | 1 | ": " | 0 | ";
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
