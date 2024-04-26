package com.example.casestudylibrary.service.book.response;

public record BookListResponse(
    Long id,
    String name,
    String description,
    String image,
    String status,
    String publisher,
    String categoryName) {
}
