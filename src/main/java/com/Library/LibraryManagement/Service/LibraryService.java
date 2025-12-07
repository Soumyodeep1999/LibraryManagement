package com.Library.LibraryManagement.Service;


import com.Library.LibraryManagement.DTO.*;
import com.Library.LibraryManagement.Exceptions.EmptyRecordException;
import com.Library.LibraryManagement.Exceptions.LibraryAlreadyExistsException;
import com.Library.LibraryManagement.Exceptions.NoAccessException;
import com.Library.LibraryManagement.Exceptions.NoRecordFoundException;
import com.Library.LibraryManagement.Message.AcknowledgementMessage;
import com.Library.LibraryManagement.Message.ErrorMessage;
import com.Library.LibraryManagement.Model.Library;
import com.Library.LibraryManagement.Repositry.BookRepo;
import com.Library.LibraryManagement.Repositry.LibraryRepo;
import com.Library.LibraryManagement.Utils.RoleSpecifierUtility;
import com.Library.LibraryManagement.Utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    LibraryRepo libraryRepo;
    @Autowired
    AcknowledgementMessage acknowledgementMessage;
    @Autowired
    ErrorMessage errorMessage;
    @Autowired
    ModelMapper mapper;
    @Autowired
    AccessGranter accessGranter;
    @Autowired
    BookRepo bookRepo;

    public boolean greeter(UserCredential userCredential){
        return accessGranter.accessChecker(userCredential, RoleSpecifierUtility.greetLibraryAccess());
    }

    public ResponseEntity<?> addLibraryDetails(LibraryRequest libraryRequest,UserCredential userCredential){
        try {
            if(accessGranter.accessChecker(userCredential,RoleSpecifierUtility.addLibraryAccess())){
                libraryRepo.save(mapper.map(libraryRequest,Library.class));
                acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.CREATED.value(),
                        "Library Details added successfully",
                        libraryRequest.getLibName()+" Library Added");
                return new ResponseEntity<>(acknowledgementMessage,HttpStatus.CREATED);
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (LibraryAlreadyExistsException e) {
            throw e;
        }
    }


    public ResponseEntity<?> retriveLibraryDetails(UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.viewLibraryAccess())){
                if(!libraryRepo.findAll().isEmpty()){
                    List<LibraryResponse> libraryResponses = new ArrayList<>();
                    List<Library> libraryList = libraryRepo.findAll();
                    for (Library library : libraryList){
                        LibraryResponse libraryResponse = mapper.map(library,LibraryResponse.class);
                        libraryResponse.setBookList(bookRepo.findByLibrary(library.getLibId())
                                .stream().map(book -> mapper.map(book, BookInLibraryResponse.class)).toList());
                        libraryResponses.add(libraryResponse);
                    }
                    return new ResponseEntity<>(libraryResponses
                            , HttpStatus.valueOf(200));
                }
                else {
                    throw new EmptyRecordException("Libray record is empty. No record to show.");
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (EmptyRecordException | NoAccessException e) {
            throw e;
        }
    }

    public ResponseEntity<?> retriveLibraryDetailsByID(int libId,UserCredential userCredential) {
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.viewLibraryAccess())){
                if (!libraryRepo.findById(libId).isEmpty()){
                    return new ResponseEntity<>(mapper.map(libraryRepo.findById(libId), LibraryResponse.class),HttpStatus.OK);
                }else {
                    throw new NoRecordFoundException("No library record found for id-"+libId);
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        } catch (NoRecordFoundException | NoAccessException e) {
            throw e;
        }
    }

    public ResponseEntity<?> deleteLibraryEntryByID(int libId,UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.deleteLibraryAccess())){
                if(!libraryRepo.findById(libId).isEmpty()) {
                    libraryRepo.deleteById(libId);
                    acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.OK.value(),
                            "Library Entry Deleted",
                            "Library Deleted for id-" + libId);
                    return new ResponseEntity<>(acknowledgementMessage, HttpStatus.OK);
                }else {
                    throw new NoRecordFoundException("No library record found for id-"+libId);
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }

        } catch (NoRecordFoundException | NoAccessException e) {
            throw e;
        }
    }

    public ResponseEntity<?> deleteAllLibraryEntry(UserCredential userCredential){
        try {
            if (accessGranter.accessChecker(userCredential,RoleSpecifierUtility.deleteLibraryAccess())){
                if (libraryRepo.count()>0){
                    libraryRepo.deleteAll();
                    acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.OK.value(),
                            "Library Entry Deleted",
                            "All library data are deleted successfully");
                    return new ResponseEntity<>(acknowledgementMessage,HttpStatus.OK);
                }else {
                    throw new EmptyRecordException("Library Record is Empty. No data to delete.");
                }
            }else {
                throw new NoAccessException("No access for this operation.");
            }
        }catch (EmptyRecordException | NoAccessException e){
            throw e;
        }
    }


}
