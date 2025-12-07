package com.Library.LibraryManagement.Utils;

import java.util.ArrayList;
import java.util.List;

public class RoleSpecifierUtility {

    public static List<String> greetLibraryAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("STUDENT");
        return role;
    }

    public static List<String> addLibraryAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("LIBRARIAN");
        return role;
    }

    public static List<String> viewLibraryAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("LIBRARIAN");
        return role;
    }

    public static List<String> updateLibraryAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("LIBRARIAN");
        return role;
    }

    public static List<String> deleteLibraryAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        return role;
    }

    public static List<String> greetBookAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("LIBRARIAN");
        role.add("STUDENT");
        return role;
    }

    public static List<String> addBookAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("LIBRARIAN");
        return role;
    }

    public static List<String> deleteBookAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("LIBRARIAN");
        return role;
    }

    public static List<String> borrowBookAccess(){
        List role = new ArrayList<String>();
        role.add("STUDENT");
        return role;
    }

    public static List<String> viewBookAccess(){
        List role = new ArrayList<String>();
        role.add("ADMIN");
        role.add("LIBRARIAN");
        return role;
    }

    public static List<String> viewBookByNameAccess(){
        List role = new ArrayList<String>();
        role.add("STUDENT");
        return role;
    }
}
