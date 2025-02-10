package rose.parser;

import rose.exceptions.RoseException;
import rose.commands.Command;

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