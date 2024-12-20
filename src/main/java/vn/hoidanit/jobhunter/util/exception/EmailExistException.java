package vn.hoidanit.jobhunter.util.exception;

public class EmailExistException extends Exception{
    public EmailExistException() {
    }

    public EmailExistException(String message) {
        super(message);
    }
}
