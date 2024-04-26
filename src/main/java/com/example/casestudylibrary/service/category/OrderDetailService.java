package com.example.casestudylibrary.service.category;

import com.example.casestudylibrary.domain.OrderDetails;
import com.example.casestudylibrary.repository.IOrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    private final IOrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(IOrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetails> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}
