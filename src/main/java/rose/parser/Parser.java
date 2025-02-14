package rose.parser;

import rose.Rose;
import rose.commands.*;
import rose.exceptions.RoseException;

import java.util.Arrays;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {
    public static Command parse(String input) throws RoseException {
        assert input != null : "Input should not be null";

        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = (parts.length > 1) ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ByeCommand();

            case "list":
                return new ListCommand();

            case "todo":
                return new AddTodoCommand(arguments);

            case "find":
                return new FindCommand(arguments);

            case "deadline":
                return parseDeadline(arguments);

            case "updateevent":
                return parseUpdateEvent(arguments);

            case "event":
                return parseEvent(arguments);

            case "mark":
                int markIndex = parseIndex(arguments);
                return new MarkCommand(markIndex);

            case "delete":
                int deleteIndex = parseIndex(arguments);
                return new DeleteCommand(deleteIndex);

            default:
                throw new RoseException("Unknown command: " + commandWord);
        }
    }

    private static Command parseDeadline(String arguments) throws RoseException {
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new RoseException("Invalid deadline format! Use: deadline <description> /by <date>");
        }
        return new AddDeadlineCommand(parts[0].trim(), parts[1].trim());
    }

    private static Command parseEvent(String arguments) throws RoseException {
        String[] parts = arguments.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new RoseException("Invalid event format! Use: event <description> /from <start> /to <end>");
        }
        return new AddEventCommand(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    private static Command parseUpdateEvent(String arguments) throws RoseException {
        // Step 1: Extract the first word (index) from the rest
        String[] firstSplit = arguments.split(" ", 2);
        if (firstSplit.length < 2) {
            throw new RoseException("Invalid format! Use: updateEvent <index> [/from <new start>] [/to <new end>]");
        }

        // Step 2: Convert index (1-based to 0-based)
        int index;
        try {
            index = Integer.parseInt(firstSplit[0].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new RoseException("Invalid task index. Please provide a valid number.");
        }

        // Step 3: Split remaining arguments by "/from" and "/to"
        String remainingArgs = firstSplit[1];

        // Step 4: Extract new from/to times
        String newFrom = null;
        String newTo = null;

        if (remainingArgs.contains("/from")) {
            String[] fromParts = remainingArgs.split("/from ", 2);
            if (fromParts.length > 1) {
                newFrom = fromParts[1].contains("/to") ? fromParts[1].split(" /to ")[0].trim() : fromParts[1].trim();
            }
        }

        if (remainingArgs.contains("/to")) {
            String[] toParts = remainingArgs.split(" /to ", 2);
            if (toParts.length > 1) {  // Ensure there's something after /to
                newTo = toParts[1].trim();
            }
        }

        return new UpdateEventCommand(index, newFrom, newTo);
    }


    private static int parseIndex(String argument) throws RoseException {
        try {
            return Integer.parseInt(argument) - 1; // Convert 1-based to 0-based index
        } catch (NumberFormatException e) {
            throw new RoseException("Invalid task index.");
        }
    }
}
