package com.example.casestudylibrary.domain;

import com.example.casestudylibrary.domain.dto.res.OrderDetailResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int quantity;

    public OrderDetailResDto toOrderDetailResDto() {
        return new OrderDetailResDto(this.book.getId(),this.book.getName(), this.quantity, this.order.getId());
    }
}
