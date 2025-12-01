package com.Library.LibraryManagement.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookID;
    private int libId;
    private String bookName;
    private String authorName;
    private String noOfAvailableCopies;
}
