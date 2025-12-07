package com.Library.LibraryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseStudent {
    private String bookName;
    private String authorName;
    private String noOfAvailableCopies;

}
