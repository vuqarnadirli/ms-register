package com.thetradingpit.msregister.exception;


public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }

    public static ClientNotFoundException of(String message) {
        return new ClientNotFoundException(message);
    }

}
