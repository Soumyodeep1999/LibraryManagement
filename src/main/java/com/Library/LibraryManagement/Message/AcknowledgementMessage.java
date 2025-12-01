package com.Library.LibraryManagement.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class AcknowledgementMessage {
    private int statusCode;
    private LocalDateTime localDateTime;
    private String message;
    private String details;
}
