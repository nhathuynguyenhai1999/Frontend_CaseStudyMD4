package com.example.casestudylibrary.repository;

import com.example.casestudylibrary.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface IBookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT new com.example.casestudylibrary.domain.Book(b.id, b.name, b.description, b.image, b.status, b.publisher, b.category)" +
            " FROM Book b JOIN b.category c " +
            "WHERE (:categoryId is null or b.id = : categoryId) AND" +
    "(b.name LIKE :search or b.description LIKE :search or c.name LIKE :search)")
    Page<Book> findAllWithSearch(String search, Long categoryId, Pageable pageable);
    void deleteAllByCategory_Id(Long categoryId);
}
