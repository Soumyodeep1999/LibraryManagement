package com.Library.LibraryManagement.Service;


import com.Library.LibraryManagement.Model.User;
import com.Library.LibraryManagement.Repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public void addUserDetails(User user){
        userRepo.save(user);
    }

    public List<User> viewUserDetails(){
        return userRepo.findAll();
    }

}
