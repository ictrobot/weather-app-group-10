package uk.ac.cam.cl.interactiondesign.group10.backend;

public class APIException extends Exception {

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

}
