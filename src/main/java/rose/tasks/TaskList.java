package rose.tasks;

import rose.exceptions.RoseException;

import java.util.ArrayList;

/**
 * The {@code TaskList} class represents a list of tasks.
 * It provides operations to manage the tasks, such as adding, removing, and retrieving tasks.
 */
public class TaskList {
    /**
     * The list of tasks being managed.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * Initializes an empty {@link ArrayList} to store tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with a given list of tasks.
     *
     * @param tasks The list of tasks to initialize the {@code TaskList}.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The {@link Task} to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list at the specified index.
     *
     * @param index The index of the task to be removed (zero-based).
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 or index >= size()).
     */
    public void removeTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve (zero-based).
     * @return The {@link Task} at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 or index >= size()).
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns all tasks in the task list.
     *
     * @return An {@link ArrayList} containing all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null : "keyword should not be empty string";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    public void updateTask(int index, Task updatedTask) throws RoseException {
        if (index < 0 || index >= tasks.size()) {
            throw new RoseException("Invalid task index.");
        }
        tasks.set(index, updatedTask);
    }

}