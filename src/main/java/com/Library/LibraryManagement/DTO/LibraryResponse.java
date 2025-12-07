package com.Library.LibraryManagement.DTO;

import com.Library.LibraryManagement.Model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse {
    private String libName ;
    private int bookCount;
    private String branchName;
    private long mobileNumber;
    private int subscriptionCount;
    private List<BookInLibraryResponse> bookList;
}
