package com.Library.LibraryManagement.DTO;

import com.Library.LibraryManagement.Model.Library;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {

    private Integer libraryId;
    private String bookName;
    private String authorName;
    private String noOfAvailableCopies;

}
