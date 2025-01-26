package rose.ui;

import rose.ui.Deadline;
import rose.ui.Event;
import rose.ui.RoseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load () throws RoseException, IOException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            File parentDir = file.getParentFile();
            if (!parentDir.exists() && !parentDir.isDirectory()) {
                if (!parentDir.mkdirs()) {
                    throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
                }
            }
            if (!file.createNewFile()) {
                throw new IOException("Failed to create file: " + file.getAbsolutePath());
            }
        } else {
            try {
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    tasks.add(parseTask(s.nextLine()));
                }
            } catch (Exception e) {
                throw new RoseException("Error parsing tasks from file: " + e.getMessage());
            }
        }
        return tasks;
    }

    // Parse a line from the file into a rose.ui.Task
    private Task parseTask(String line) throws RoseException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
            case "T":
                return new Todo(parts[2], isDone);
            case "D":
                return new Deadline(parts[2], parts[3], isDone);
            case "E":
                return new Event(parts[2], parts[3], parts[4], isDone);
            default:
                throw new RoseException("Corrupted data file! Unable to parse task.");
        }
    }

    // Save tasks to the file
    // Save all tasks to the file
    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) { // Overwrite the file
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
    }
}
