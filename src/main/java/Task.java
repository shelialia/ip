public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType tasktype;

    public Task(String description, TaskType tasktype) {
        this.description = description;
        this.isDone = false;
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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
