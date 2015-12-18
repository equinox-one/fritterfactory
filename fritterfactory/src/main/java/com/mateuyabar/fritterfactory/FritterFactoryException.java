package com.mateuyabar.fritterfactory;

/**
 * Created by mateuyabar on 17/12/15.
 */
public class FritterFactoryException extends RuntimeException {
    public FritterFactoryException() {
    }

    public FritterFactoryException(String message) {
        super(message);
    }

    public FritterFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FritterFactoryException(Throwable cause) {
        super(cause);
    }

    public FritterFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
