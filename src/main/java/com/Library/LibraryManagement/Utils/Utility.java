package com.Library.LibraryManagement.Utils;


import com.Library.LibraryManagement.Message.AcknowledgementMessage;
import com.Library.LibraryManagement.Message.ErrorMessage;

import java.time.LocalDateTime;

public class Utility {

    public static ErrorMessage errorMessageGenerator(int statusCode, Exception e, String messageDetails){
        return ErrorMessage.builder()
                .statusCode(statusCode)
                .errorMessage(e.getMessage())
                .details(messageDetails)
                .build();
    }

    public static AcknowledgementMessage acknowledgementMessageGenerator(int statusCode, String message, String messageDetails){
        return AcknowledgementMessage.builder()
                .statusCode(statusCode)
                .localDateTime(LocalDateTime.now())
                .message(message)
                .details(messageDetails)
                .build();
    }

}
