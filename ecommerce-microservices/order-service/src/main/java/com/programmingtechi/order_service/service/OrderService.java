package com.programmingtechi.order_service.service;

import com.programmingtechi.order_service.dto.InventoryResponse;
import com.programmingtechi.order_service.dto.OrderLineItemsDto;
import com.programmingtechi.order_service.dto.OrderRequest;
import com.programmingtechi.order_service.model.Order;
import com.programmingtechi.order_service.model.OrderLineItems;
import com.programmingtechi.order_service.model.OrderStatus;
import com.programmingtechi.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBilder;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.PLACED);

        List<OrderLineItems> listOrderLineItems = orderRequest.getOrderLineItemsDtos()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(listOrderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //Запрос к Inventory Service, и оформление заказа, если продукт есть в наличии
        InventoryResponse[] inventoryResponsesArray = webClientBilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock);
        if(allProductsInStock){
        orderRepository.save(order);

    }else {
        throw new IllegalArgumentException("Product is not in stock, try again later");
    }
    }
    public void updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
//            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Order not found");
        }

        order.setOrderStatus(status);
        orderRepository.save(order);
    }
    public List<Order> getAllPlacedOrders() {
        return orderRepository.findByOrderStatus(OrderStatus.PLACED);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
