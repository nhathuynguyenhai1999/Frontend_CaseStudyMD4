package com.example.casestudylibrary.repository;

import com.example.casestudylibrary.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
