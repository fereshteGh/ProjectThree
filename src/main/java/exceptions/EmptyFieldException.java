package exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("Filed is empty");
    }
}
