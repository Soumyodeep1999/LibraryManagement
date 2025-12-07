package com.Library.LibraryManagement.Controller;

import com.Library.LibraryManagement.DTO.LibraryRequest;
import com.Library.LibraryManagement.Service.LibraryService;
import com.Library.LibraryManagement.Utils.CredentialGeneratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping("/")
    public ResponseEntity<?> greet(@RequestParam String name , @RequestParam String password){
        return new ResponseEntity<>("Welcome to Library ---"+libraryService.greeter(CredentialGeneratorUtility.credentialGenerator(name,password)),
                HttpStatus.valueOf(200));
    }

    @PostMapping("/entry")
    public ResponseEntity<?> addLibrary(@RequestBody LibraryRequest libraryRequest,@RequestParam String name , @RequestParam String password){
        return libraryService.addLibraryDetails(libraryRequest,
                CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @GetMapping("/entry")
    public ResponseEntity<?> viewLibrary(@RequestParam String name , @RequestParam String password){
        return libraryService.retriveLibraryDetails(CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @GetMapping("/entry/{libId}")
    public ResponseEntity<?> viewLibrary(@PathVariable int libId,@RequestParam String name , @RequestParam String password){

        return libraryService.retriveLibraryDetailsByID(libId,
                CredentialGeneratorUtility.credentialGenerator(name,password));
    }


    @DeleteMapping("/delete/{libId}")
    public ResponseEntity<?> deleteLibraryByID(@PathVariable int libId,@RequestParam String name , @RequestParam String password){
        return libraryService.deleteLibraryEntryByID(libId,
                CredentialGeneratorUtility.credentialGenerator(name,password));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFullLibrary(@RequestParam String name , @RequestParam String password){
        return libraryService.deleteAllLibraryEntry(CredentialGeneratorUtility.credentialGenerator(name,password));
    }
}
