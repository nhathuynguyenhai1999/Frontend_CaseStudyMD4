package com.example.casestudylibrary.service.book.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSaveRequest{
    private String name;
    private String description;
    private Long categoryId;
    private String publisher;
    private Boolean status;
}
