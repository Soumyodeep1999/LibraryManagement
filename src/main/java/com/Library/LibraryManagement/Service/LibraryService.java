package com.Library.LibraryManagement.Service;


import com.Library.LibraryManagement.DTO.LibraryRequest;
import com.Library.LibraryManagement.DTO.LibraryResponse;
import com.Library.LibraryManagement.Exceptions.EmptyRecordException;
import com.Library.LibraryManagement.Exceptions.LibraryAlreadyExistsException;
import com.Library.LibraryManagement.Exceptions.NoRecordFoundException;
import com.Library.LibraryManagement.Message.AcknowledgementMessage;
import com.Library.LibraryManagement.Message.ErrorMessage;
import com.Library.LibraryManagement.Model.Library;
import com.Library.LibraryManagement.Repositry.LibraryRepo;
import com.Library.LibraryManagement.Utils.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<?> addLibraryDetails(LibraryRequest libraryRequest){
        try {
            if(true){
                libraryRepo.save(mapper.map(libraryRequest,Library.class));
                acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.CREATED.value(),
                        "Library Details added successfully",
                        libraryRequest.getLibName()+" Library Added");
                return new ResponseEntity<>(acknowledgementMessage,HttpStatus.CREATED);
            }else {
                throw new LibraryAlreadyExistsException("Library Already Exists.");
            }
        } catch (LibraryAlreadyExistsException e) {
            throw e;
        }
    }


    public ResponseEntity<?> retriveLibraryDetails(){
        try {
            if(!libraryRepo.findAll().isEmpty()){
                return new ResponseEntity<>(libraryRepo.findAll().stream()
                        .map(library -> mapper.map(library, LibraryResponse.class))
                        .toList()
                        , HttpStatus.valueOf(200));
            }
            else {
                throw new EmptyRecordException("Libray record is empty. No record to show.");
            }
        } catch (EmptyRecordException e) {
            throw e;
        }
    }

    public ResponseEntity<?> retriveLibraryDetailsByID(int libId) {
        try {
            if (!libraryRepo.findById(libId).isEmpty()){
                return new ResponseEntity<>(mapper.map(libraryRepo.findById(libId), LibraryResponse.class),HttpStatus.OK);
            }else {
                throw new NoRecordFoundException("No library record found for id-"+libId);
            }
        } catch (NoRecordFoundException e) {
            throw e;
        }
    }

    public ResponseEntity<?> deleteLibraryEntryByID(int libId){
        try {
            if(!libraryRepo.findById(libId).isEmpty()){
                libraryRepo.deleteById(libId);
                acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.OK.value(),
                        "Library Entry Deleted",
                        "Library Deleted for id-"+libId);
                return new ResponseEntity<>(acknowledgementMessage,HttpStatus.OK);
            }
            else {
                throw new NoRecordFoundException("No library record found for id-"+libId);
            }
        } catch (NoRecordFoundException e) {
            throw e;
        }
    }

    public ResponseEntity<?> deleteAllLibraryEntry(){
        try {
            if (libraryRepo.count()>0){
                libraryRepo.deleteAll();
                acknowledgementMessage = Utility.acknowledgementMessageGenerator(HttpStatus.OK.value(),
                        "Library Entry Deleted",
                        "All library data are deleted successfully");
                return new ResponseEntity<>(acknowledgementMessage,HttpStatus.OK);
            }else {
                throw new EmptyRecordException("Library Record is Empty. No data to delete.");
            }
        }catch (EmptyRecordException e){
            throw e;
        }
    }


}
