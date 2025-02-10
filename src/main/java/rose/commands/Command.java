package rose.commands;

/**
 * Represents a command parsed from user input, containing the command type and its arguments.
 */
public class Command {
    private String command;
    private String arguments;

    /**
     * Constructs a {@code Command} with the specified command type and arguments.
     *
     * @param command   The type of command (e.g., "add", "delete", "list").
     * @param arguments The arguments for the command (can be empty if no arguments are provided).
     */
    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * Returns the command type.
     *
     * @return The command type as a string.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the arguments for the command.
     *
     * @return The command arguments as a string.
     */
    public String getArguments() {
        return arguments;
    }
}