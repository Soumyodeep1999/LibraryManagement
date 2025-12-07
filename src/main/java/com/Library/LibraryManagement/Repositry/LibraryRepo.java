package com.Library.LibraryManagement.Repositry;

import com.Library.LibraryManagement.Model.Library;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepo extends JpaRepository<Library,Integer> {

    @Transactional
    @Modifying
    @Query(value = "update library set book_count= :bookCount where lib_id= :libId ;",nativeQuery = true)
    public int updateBookCount(@Param("libId") int libId , @Param("bookCount") int bookCount);
}
