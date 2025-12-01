package com.Library.LibraryManagement.Controller;

import com.Library.LibraryManagement.DTO.LibraryRequest;
import com.Library.LibraryManagement.Model.Library;
import com.Library.LibraryManagement.Service.LibraryService;
import jakarta.websocket.server.PathParam;
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
    public ResponseEntity<?> greet(@RequestParam(defaultValue = "Student")String role){
        return new ResponseEntity<>("Welcome to Library ---"+role, HttpStatus.valueOf(200));
    }

    @PostMapping("/entry")
    public ResponseEntity<?> addLibrary(@RequestBody LibraryRequest libraryRequest){
        return libraryService.addLibraryDetails(libraryRequest);
    }

    @GetMapping("/entry")
    public ResponseEntity<?> viewLibrary(){
        return libraryService.retriveLibraryDetails();
    }

    @GetMapping("/entry/{libId}")
    public ResponseEntity<?> viewLibrary(@PathVariable int libId){

        return libraryService.retriveLibraryDetailsByID(libId);
    }


    @DeleteMapping("/delete/{libId}")
    public ResponseEntity<?> deleteLibraryByID(@PathVariable int libId){
        return libraryService.deleteLibraryEntryByID(libId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFullLibrary(){
        return libraryService.deleteAllLibraryEntry();
    }
}
