public class EmptyInputException extends Exception {

    public EmptyInputException() {
        super("No input entered.");
    }

    public EmptyInputException(String location) {
        super("No input entered for " + location + ".");
    }
}
