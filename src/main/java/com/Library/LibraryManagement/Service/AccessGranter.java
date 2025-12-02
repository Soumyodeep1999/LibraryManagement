package com.Library.LibraryManagement.Service;

import com.Library.LibraryManagement.DTO.UserCredential;
import com.Library.LibraryManagement.Model.User;
import com.Library.LibraryManagement.Repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.List;

@Component
public class AccessGranter {

    @Autowired
    UserRepo userRepo;

    public boolean accessChecker(UserCredential userCredential , List<String> expectedRoles){
        boolean grant = false ;
        if(!userRepo.findByName(userCredential.getName()).isEmpty()){
            List<User> foundUserList = userRepo.findByName(userCredential.getName());
            for(User foundUser : foundUserList){
                if(foundUser.getPassword().equals(userCredential.getPassword())){
                    // Check Role
                    if (expectedRoles.contains(foundUser.getRole())){
                        grant = true ;
                        break;
                    }
                }
            }
        }
        return grant;
    }
}
