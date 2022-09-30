package com.app.exceptions;

public class InvalidRemoteURLException extends  FrameworkException{

    public InvalidRemoteURLException(String message)
    {
        super(message);
    }

    public InvalidRemoteURLException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
