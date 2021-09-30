package jpabook.jpashop.repository.simplequery;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    private static OrderSimpleQueryDto createSimpleOrderDto(Order o) {
        return new OrderSimpleQueryDto(o.getId(), o.getMember().getName(),
                o.getOrderDate(), o.getStatus(), o.getDelivery().getAddress());
    }


}