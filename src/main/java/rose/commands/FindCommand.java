package rose.commands;

import rose.exceptions.RoseException;
import rose.storage.Storage;
import rose.tasks.Task;
import rose.tasks.TaskList;
import rose.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RoseException, IOException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        return ui.displayTasks(matchingTasks);
    }
}
