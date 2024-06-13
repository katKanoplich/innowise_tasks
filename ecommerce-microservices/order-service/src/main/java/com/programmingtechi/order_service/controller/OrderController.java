package com.programmingtechi.order_service.controller;

import com.programmingtechi.order_service.dto.OrderRequest;
import com.programmingtechi.order_service.model.Order;
import com.programmingtechi.order_service.model.OrderStatus;
import com.programmingtechi.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
    @PostMapping("/{id}/placed")
       public void completeOrder(@PathVariable("id") Long orderId) {
        orderService.updateOrderStatus(orderId, OrderStatus.PLACED);
    }
    @GetMapping("/orders-history")
    public List<Order> getAllOrders() {
       return orderService.getAllPlacedOrders();
    }
}
