package com.example.casestudylibrary.controller.rest;

import com.example.casestudylibrary.domain.OrderDetails;
import com.example.casestudylibrary.service.category.OrderDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderDetailRestAPI {
    private final OrderDetailService orderDetailService;
    @Autowired
    public OrderDetailRestAPI(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

}
