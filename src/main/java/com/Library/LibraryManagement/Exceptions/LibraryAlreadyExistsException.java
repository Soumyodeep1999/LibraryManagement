package com.Library.LibraryManagement.Exceptions;

public class LibraryAlreadyExistsException extends RuntimeException{

    public LibraryAlreadyExistsException(String message){
        super(message);
    }

}
