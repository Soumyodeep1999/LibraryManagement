package com.Library.LibraryManagement.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInLibraryResponse {

    private String bookName;
    private String authorName;
    private String noOfAvailableCopies;

}
