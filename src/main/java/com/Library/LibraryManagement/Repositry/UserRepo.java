package com.Library.LibraryManagement.Repositry;

import com.Library.LibraryManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    public List<User> findByName(String name);
}
