package com.example.casestudylibrary.service;


import com.example.casestudylibrary.domain.Order;
import com.example.casestudylibrary.domain.dto.req.OrderReqDto;
import com.example.casestudylibrary.domain.dto.req.StatusOrderReqDto;
import com.example.casestudylibrary.domain.dto.res.OrderResDto;

import java.util.List;

public interface IOrderService {
    List<OrderResDto> findAll();
    OrderResDto findOrdeById(Long id);
    OrderResDto save(OrderReqDto orderReqDto);
    void delete(Long id);
    void updateStatusOrderById(Long id);
}
