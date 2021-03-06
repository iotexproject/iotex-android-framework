package com.github.iotexproject.antenna.keystore;

/**
 * cipher exception.
 *
 * @author Yang XuePing
 */
public class CipherException extends RuntimeException {
    public CipherException(String message) {
        super(message);
    }

    public CipherException(Throwable cause) {
        super(cause);
    }

    public CipherException(String message, Throwable cause) {
        super(message, cause);
    }
}
