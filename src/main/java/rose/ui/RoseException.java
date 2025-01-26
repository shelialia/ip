package rose.ui;

/**
 * The {@code RoseException} class represents a custom exception used in the Rose application.
 * It is thrown when an error specific to the application occurs.
 */
public class RoseException extends Exception {

    /**
     * Constructs a {@code RoseException} with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public RoseException(String message) {
        super(message);
    }
}
