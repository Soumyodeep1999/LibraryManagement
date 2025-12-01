package com.Library.LibraryManagement.Repositry;

import com.Library.LibraryManagement.Model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepo extends JpaRepository<Library,Integer> {
}
