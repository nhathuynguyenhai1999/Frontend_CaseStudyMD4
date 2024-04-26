package com.example.casestudylibrary.domain;

import com.example.casestudylibrary.domain.dto.res.OrderDetailResDto;
import com.example.casestudylibrary.domain.dto.res.OrderResDto;
import com.example.casestudylibrary.domain.enumration.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate borrowDate;
    private LocalDate payDate;
    @OneToOne
    private Book book;
    private EStatus eStatus;


    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;

    public OrderResDto toOrderResDto() {

        List<OrderDetailResDto> orderDetailResDtos = this.orderDetails
                .stream().map(odt -> {
                    return new OrderDetailResDto(odt.getBook().getId(), odt.getBook().getName(), odt.getQuantity(), odt.getId());
                }).collect(Collectors.toList());
        return new OrderResDto(
                this.id,
                this.borrowDate,
                this.payDate,
                orderDetailResDtos,
                this.eStatus,
                this.user.toUserResDto()
        );
    }
}

