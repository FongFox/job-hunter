package vn.hoidanit.jobhunter.util.exception;

public class IdInvalidException extends Exception {
    // Parameterless Constructor
    public IdInvalidException() {
    }

    // Constructor that accepts a message
    public IdInvalidException(String message) {
        super(message);
    }

}
