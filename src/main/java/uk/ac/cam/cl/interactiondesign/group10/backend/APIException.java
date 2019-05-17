package uk.ac.cam.cl.interactiondesign.group10.backend;

public class APIException extends Exception {

    APIException(String message) {
        super(message);
    }

    APIException(String message, Throwable cause) {
        super(message, cause);
    }

}
