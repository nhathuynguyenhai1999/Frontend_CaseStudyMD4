package com.example.casestudylibrary.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResDto {

    private Long bookId;
    private String bookName;
    private Integer quantity;
    private Long orderDetailId;


}