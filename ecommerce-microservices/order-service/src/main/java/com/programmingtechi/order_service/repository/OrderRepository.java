package com.programmingtechi.order_service.repository;

import com.programmingtechi.order_service.model.Order;
import com.programmingtechi.order_service.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderStatus(OrderStatus status);

}
