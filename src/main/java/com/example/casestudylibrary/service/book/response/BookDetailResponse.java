package com.example.casestudylibrary.service.book.response;

public record BookDetailResponse(Long id,
    String name,
    String description,
    String image, String status, String publisher, Long categoryId) {
}
