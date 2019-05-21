package uk.ac.cam.cl.interactiondesign.group10.backend;

//Just an exception class for an specific expection in our code
public class APIException extends Exception {

    APIException(String message) {
        super(message);
    }

    APIException(String message, Throwable cause) {
        super(message, cause);
    }

}
