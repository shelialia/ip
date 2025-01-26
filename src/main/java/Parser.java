public class Parser {
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

class Command {
    private String command;
    private String arguments;

    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }
}