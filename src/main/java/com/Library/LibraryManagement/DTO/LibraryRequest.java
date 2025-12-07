package com.Library.LibraryManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryRequest {
    private String libName ;
    private int bookCount;
    private String branchName;
    private long mobileNumber;
    private int subscriptionCount;
}
