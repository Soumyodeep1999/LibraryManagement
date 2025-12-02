package com.Library.LibraryManagement.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userID;
    private String name;
    private String userEmail;
    private String password;
    private enum Role{
        STUDENT,
        LIBRARIAN,
        ADMIN
    }
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getRole(){
        return this.role.toString();
    }

}
