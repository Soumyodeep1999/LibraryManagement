package com.Library.LibraryManagement.Utils;

import com.Library.LibraryManagement.DTO.UserCredential;

public class CredentialGeneratorUtility {

    public static UserCredential credentialGenerator(String name,String password){
        return UserCredential.builder()
                .name(name).password(password).build();
    }

}
