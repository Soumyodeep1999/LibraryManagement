package com.Library.LibraryManagement.Repositry;

import com.Library.LibraryManagement.Model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book,Integer> {

    @Query(value = "select * from book where book_name = :bookName",nativeQuery = true)
    public List<Book> findByName(@Param("bookName") String bookName);

    @Query(value = "select * from book where library_id = :libraryId",nativeQuery = true)
    public List<Book> findByLibrary(@Param("libraryId") int libraryId);

    @Query(value = "select * from book where library_id= :library_id and book_name= :bookName and author_name= :authorName ;",nativeQuery = true)
    public List<Book> submitBookEntryCheck(@Param("library_id") int library_id, @Param("bookName") String bookName,
                                        @Param("authorName") String authorName);



    @Query(value = "select * from book where library_id= :library_id and book_name= :bookName and author_name= :authorName and \n" +
            "no_of_available_copies > 0;",nativeQuery = true)
    public List<Book> findAvailableBook(@Param("library_id") int library_id, @Param("bookName") String bookName,
                                        @Param("authorName") String authorName);

    @Transactional
    @Modifying
    @Query(value = "update book\n" +
            "set no_of_available_copies = :noOfAvailableCopies \n" +
            "where library_id= :libraryId and book_name= :bookName and author_name= :authorName and \n" +
            "no_of_available_copies > 0;",nativeQuery = true)
    public int updateNoOfCopies(@Param("noOfAvailableCopies") String noOfAvailableCopies,@Param("libraryId") int libraryId
    ,@Param("bookName") String bookName,@Param("authorName") String authorName);
}
