package com.example.casestudylibrary.service.impl;


import com.example.casestudylibrary.domain.Order;
import com.example.casestudylibrary.domain.OrderDetails;
import com.example.casestudylibrary.domain.User;
import com.example.casestudylibrary.domain.dto.req.OrderDetailReqDto;
import com.example.casestudylibrary.domain.dto.req.OrderReqDto;
import com.example.casestudylibrary.domain.dto.req.StatusOrderReqDto;
import com.example.casestudylibrary.domain.dto.res.OrderDetailResDto;
import com.example.casestudylibrary.domain.dto.res.OrderResDto;
import com.example.casestudylibrary.domain.enumration.EStatus;
import com.example.casestudylibrary.repository.IOrderDetailRepository;
import com.example.casestudylibrary.repository.IOrderRepository;
import com.example.casestudylibrary.repository.IUserRepository;
import com.example.casestudylibrary.service.IOrderService;
import com.example.casestudylibrary.service.book.IBookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService implements IOrderService {
    private final IUserRepository userRepository;
    private final IOrderRepository orderRepository;
    private final IBookService bookService;

    private final IOrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderResDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResDto> orderResDtos = new ArrayList<>();
        for (Order order : orders) {
            orderResDtos.add(order.toOrderResDto());
        }
        return orderResDtos;
    }


    @Override
    public OrderResDto findOrdeById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            return order.toOrderResDto();
        }
        return null;

    }


    @Override
    public OrderResDto save(OrderReqDto orderReqDto) {
        Order order = new Order();
        order.setBorrowDate(LocalDate.parse(orderReqDto.getBorrowDate()));
        order.setPayDate(LocalDate.parse(orderReqDto.getPayDate()));

        User user = userRepository.findById(orderReqDto.getUserId()).orElse(null);
        order.setUser(user);
        order.setEStatus(EStatus.AVAILABLE);

        List<OrderDetails> orderDetails = new ArrayList<>();
        for(OrderDetailReqDto item : orderReqDto.getOrderDetails()){
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setBook(bookService.findBookById(item.getBookId()));
            orderDetail.setOrder(order);

            orderDetailRepository.save(orderDetail);
            orderDetails.add(orderDetail);
        }

        orderRepository.save(order);


        OrderResDto orderResDto = new OrderResDto();

        orderResDto.setId(order.getId());
        orderResDto.setBorrowDate(order.getBorrowDate());
        orderResDto.setPayDate(order.getPayDate());
        orderResDto.setEStatus(order.getEStatus());

        List<OrderDetailResDto> orderDetailResDtos = orderDetails.
                stream().map(orderDetail -> orderDetail.toOrderDetailResDto()).collect(Collectors.toList());

        orderResDto.setOrderDetailResDtos(orderDetailResDtos);
        return orderResDto;

    }



    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);

    }

    @Override
    public void updateStatusOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return;
        }
        if (order.getEStatus().equals(EStatus.AVAILABLE)) {
            order.setEStatus(EStatus.UNAVAILABLE);
        } else if (order.getEStatus().equals(EStatus.UNAVAILABLE)) {
            order.setEStatus(EStatus.AVAILABLE);
        }
        orderRepository.save(order);
    }


}

