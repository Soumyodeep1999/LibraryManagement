package com.Library.LibraryManagement.Controller;

import com.Library.LibraryManagement.DTO.BookRequest;
import com.Library.LibraryManagement.DTO.BookRequestStudent;
import com.Library.LibraryManagement.DTO.UserCredential;
import com.Library.LibraryManagement.Service.BookService;
import com.Library.LibraryManagement.Utils.CredentialGeneratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public ResponseEntity<?> greet(@RequestParam String name, @RequestParam String password){
        return bookService.greetBook(CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest , @RequestParam String name, @RequestParam String password){
        return bookService.addBook(bookRequest,CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @GetMapping("/list")
    public ResponseEntity<?> viewBookList(@RequestParam String name, @RequestParam String password){
        return bookService.retriveBookList(CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @GetMapping("/{bookName}")
    public ResponseEntity<?> viewBookListByName(@PathVariable String bookName ,@RequestParam String name, @RequestParam String password){
        return bookService.viewBookByName(bookName,CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BookRequestStudent bookRequestStudent, @RequestParam String name, @RequestParam String password){
        return bookService.borrowBook(bookRequestStudent,CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitBook(@RequestBody BookRequestStudent bookRequestStudent, @RequestParam String name, @RequestParam String password){
        return bookService.submitBook(bookRequestStudent,CredentialGeneratorUtility.credentialGenerator(name,password));
    }


}
