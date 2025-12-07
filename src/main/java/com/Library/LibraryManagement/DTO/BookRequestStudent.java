package com.Library.LibraryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestStudent {

    private Integer libraryId;
    private String bookName;
    private String authorName;

}
