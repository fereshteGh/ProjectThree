package exceptions;

public class FieldEmptyException extends Exception {
    public FieldEmptyException() {
        super("Filed is empty");
    }
}
