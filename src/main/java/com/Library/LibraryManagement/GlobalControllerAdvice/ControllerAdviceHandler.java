package com.Library.LibraryManagement.GlobalControllerAdvice;

import com.Library.LibraryManagement.Exceptions.EmptyRecordException;
import com.Library.LibraryManagement.Exceptions.LibraryAlreadyExistsException;
import com.Library.LibraryManagement.Exceptions.NoRecordFoundException;
import com.Library.LibraryManagement.Utils.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdviceHandler {


    @ExceptionHandler(LibraryAlreadyExistsException.class)
    public ResponseEntity<?> handlelibraryAlreadyExistsException(LibraryAlreadyExistsException e){
        return new ResponseEntity<>(Utility.errorMessageGenerator(HttpStatus.BAD_REQUEST.value(), e,
                "Dublicate library id used ,please use unique library id .")
                ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyRecordException.class)
    public ResponseEntity<?> handleEmptyRecordException(EmptyRecordException e){
        return new ResponseEntity<>(Utility.errorMessageGenerator(HttpStatus.BAD_REQUEST.value(), e,
                "Record is empty"),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoRecordFoundException.class)
    public ResponseEntity<?> handleNoRecordFoundException(NoRecordFoundException e){
        return  new ResponseEntity<>(Utility.errorMessageGenerator(HttpStatus.NOT_FOUND.value(), e,
                "No record found"),HttpStatus.NOT_FOUND);
    }

}
