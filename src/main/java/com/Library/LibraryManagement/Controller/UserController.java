package com.Library.LibraryManagement.Controller;


import com.Library.LibraryManagement.Model.User;
import com.Library.LibraryManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUserDetails(user);
    }

    @GetMapping("/view")
    public List<User> viewUser(){
        return userService.viewUserDetails();
    }


}
