package com.Library.LibraryManagement.Service;

import com.Library.LibraryManagement.DTO.*;
import com.Library.LibraryManagement.Exceptions.NoAccessException;
import com.Library.LibraryManagement.Exceptions.NoRecordFoundException;
import com.Library.LibraryManagement.Message.AcknowledgementMessage;
import com.Library.LibraryManagement.Model.Book;
import com.Library.LibraryManagement.Repositry.BookRepo;
import com.Library.LibraryManagement.Repositry.LibraryRepo;
import com.Library.LibraryManagement.Utils.RoleSpecifierUtility;
import com.Library.LibraryManagement.Utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AccessGranter accessGranter;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    LibraryRepo libraryRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AcknowledgementMessage acknowledgementMessage;

    public ResponseEntity<?> greetBook(UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential, RoleSpecifierUtility.greetBookAccess())){
                return new ResponseEntity<>("Welcome to Book Page", HttpStatus.OK);
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (NoAccessException e) {
            throw e;
        }
    }

    public ResponseEntity<?> addBook(BookRequest bookRequest, UserCredential userCredential) {
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.addBookAccess())){
                if (!libraryRepo.findById(bookRequest.getLibraryId()).isEmpty()){
                    Book book = new Book();
                    book.setBookName(bookRequest.getBookName());
                    book.setAuthorName(bookRequest.getAuthorName());
                    book.setNoOfAvailableCopies(bookRequest.getNoOfAvailableCopies());
                    book.setLibraryId(bookRequest.getLibraryId());
                    bookRepo.save(book);
                    libraryRepo.updateBookCount(bookRequest.getLibraryId()
                    ,libraryRepo.findById(bookRequest.getLibraryId()).get().getBookCount()+1);
                    return new ResponseEntity<>(Utility.acknowledgementMessageGenerator(HttpStatus.CREATED.value(),
                            "Book added successfully","Book added to library")
                    ,HttpStatus.CREATED);
                }else {
                    throw new NoRecordFoundException("Library not found.");
                }

            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (NoAccessException | NoRecordFoundException e) {
            throw e;
        }
    }

    public ResponseEntity<?> retriveBookList(UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.viewBookAccess())){
                if(bookRepo.count()>0){
                    return new ResponseEntity<>(bookRepo.findAll().stream()
                            .map(book -> modelMapper.map(book,BookResponse.class))
                            .toList(),HttpStatus.OK);
                }else {
                    throw new NoRecordFoundException("Book list is Empty.");
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (NoAccessException | NoRecordFoundException e) {
            throw e;
        }
    }



    public ResponseEntity<?> viewBookByName(String bookName , UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.viewBookAccess())){
                if(!bookRepo.findByName(bookName).isEmpty()){
                    return new ResponseEntity<>(
                            bookRepo.findByName(bookName).stream().map(
                                    book -> modelMapper.map(book, BookResponseStudent.class)).toList(),HttpStatus.OK);
                }else {
                    throw new NoRecordFoundException("No record found for "+bookName);
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (NoAccessException | NoRecordFoundException e) {
            throw e;
        }
    }

    public ResponseEntity<?> borrowBook(BookRequestStudent bookRequestStudent ,UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.borrowBookAccess())){
                List<Book> availableBook = bookRepo.findAvailableBook(bookRequestStudent.getLibraryId()
                        ,bookRequestStudent.getBookName(),bookRequestStudent.getAuthorName());
                if(!availableBook.isEmpty()){
                    String noOfAvailableCopies = availableBook.get(0).getNoOfAvailableCopies();
                    noOfAvailableCopies = String.valueOf((Integer.parseInt(noOfAvailableCopies)-1));
                    bookRepo.updateNoOfCopies(noOfAvailableCopies,bookRequestStudent.getLibraryId()
                            ,bookRequestStudent.getBookName(),bookRequestStudent.getAuthorName());
                    acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.OK.value()
                            ,"Book borrowed successfully."
                            ,bookRequestStudent.getBookName()+" by "+bookRequestStudent.getAuthorName()+" added under your name");
                    return new ResponseEntity<>(acknowledgementMessage,HttpStatus.OK);
                }else {
                    throw new NoRecordFoundException("Requested book currently not available.");
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> submitBook(BookRequestStudent bookRequestStudent ,UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.borrowBookAccess())){
                List<Book> availableBook = bookRepo.submitBookEntryCheck(bookRequestStudent.getLibraryId()
                        ,bookRequestStudent.getBookName(),bookRequestStudent.getAuthorName());
                if(!availableBook.isEmpty()){
                    String noOfAvailableCopies = availableBook.get(0).getNoOfAvailableCopies();
                    noOfAvailableCopies = String.valueOf((Integer.parseInt(noOfAvailableCopies)+1));
                    bookRepo.updateNoOfCopies(noOfAvailableCopies,bookRequestStudent.getLibraryId()
                            ,bookRequestStudent.getBookName(),bookRequestStudent.getAuthorName());
                    acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.OK.value()
                            ,"Book submitted successfully."
                            ,bookRequestStudent.getBookName()+" by "+bookRequestStudent.getAuthorName()+" submitted under your name.");
                    return new ResponseEntity<>(acknowledgementMessage,HttpStatus.OK);
                }else {
                    throw new NoRecordFoundException("This is invalid book for this library.");
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
