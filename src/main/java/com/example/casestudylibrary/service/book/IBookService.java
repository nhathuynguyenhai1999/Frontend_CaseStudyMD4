package com.example.casestudylibrary.service.book;

import com.example.casestudylibrary.domain.Book;
import com.example.casestudylibrary.domain.dto.res.BookResDto;
import com.example.casestudylibrary.service.book.request.BookSaveRequest;
import com.example.casestudylibrary.service.book.response.BookDetailResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBookService {
    List<BookResDto> findAll();
    Page<Book> findAllWithSearch(String search, Long categoryId, Pageable pageable);
    void create (BookSaveRequest request);
    void create(BookSaveRequest req, MultipartFile img, HttpServletRequest request) throws IOException;
    void update (BookSaveRequest request, Long id) throws JsonMappingException;

    //    @Override
    //    public void update(BookSaveRequest request, Long id) throws JsonMappingException {
    //        Book book = findBookById(id);
    //        objectMapper.updateValue(book, request);
    //        bookRepository.save(book);
    //    }
    void update(Long bookId, BookSaveRequest req, MultipartFile img, HttpServletRequest request) throws IOException;

    void delete (Long id);
    Book findBookById(Long id);
    BookDetailResponse findBookDetailById(Long id);
    long count();
    void init();

//    Book MultipartFile(String img);
}
