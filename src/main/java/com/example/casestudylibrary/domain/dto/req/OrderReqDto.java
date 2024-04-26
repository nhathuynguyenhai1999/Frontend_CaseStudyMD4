package com.example.casestudylibrary.domain.dto.req;

import com.example.casestudylibrary.domain.OrderDetails;
import com.example.casestudylibrary.domain.dto.res.BookResDto;
import com.example.casestudylibrary.domain.enumration.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {
    private String borrowDate;
    private String payDate;
    private List<OrderDetailReqDto> orderDetails;
    private String status;
    private Long userId;
}
