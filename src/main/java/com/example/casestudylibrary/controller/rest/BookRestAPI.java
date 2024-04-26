package com.example.casestudylibrary.controller.rest;

import com.example.casestudylibrary.domain.Book;
import com.example.casestudylibrary.service.book.IBookService;
import com.example.casestudylibrary.service.book.request.BookSaveRequest;
import com.example.casestudylibrary.service.book.response.BookDetailResponse;
import com.example.casestudylibrary.validation.ExistsEntity;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookRestAPI {
    private final IBookService bookService;

    @Autowired
    private HttpServletRequest request;

    public BookRestAPI(IBookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<Page<Book>> findAllWithSearch(@RequestParam(required = false, defaultValue = "") String search, @RequestParam(required = false) Long categoryId, @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(bookService.findAllWithSearch(search, categoryId, pageable));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid BookSaveRequest req, @RequestParam MultipartFile img) throws IOException {


        bookService.create(req, img, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid BookSaveRequest req,@RequestParam MultipartFile img, @PathVariable Long id) throws IOException {
        bookService.update(id, req, img, request );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailResponse> findById(@ExistsEntity(value = Book.class, message = "Book not found" ) @PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookDetailById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }
}
