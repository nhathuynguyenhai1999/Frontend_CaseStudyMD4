package com.example.casestudylibrary.domain.dto.req;

import com.example.casestudylibrary.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReqDto {

    private String name;
    private String description;
    private String publisher;
    private String status;
    private Image image;
    private Long categoryId;
}
