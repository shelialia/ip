package rose.ui;

/**
 * The {@code Parser} class provides functionality to parse user input into executable commands.
 */
public class Parser {

    /**
     * Parses the user input into a {@link Command} object.
     *
     * @param input The user input as a string.
     * @return A {@link Command} object representing the parsed user input.
     * @throws RoseException If the input is empty.
     */
    public static Command parse(String input) throws RoseException {
        if (input.isEmpty()) {
            throw new RoseException("Empty tasks are not accepted. Please try again.");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        if (parts.length == 1) {
            return new Command(command, "");
        }

        return new Command(command, parts[1].trim());
    }
}

/**
 * Represents a command parsed from user input, containing the command type and its arguments.
 */
class Command {
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