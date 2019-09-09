package com.gruporyc.restaurant.services;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.OrderResponseDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;

import java.util.List;

/**
 * OrderServiceManager: Public interface to expose Order service implementation
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
public interface OrderServiceManager{
    SimpleResponse createOrder(OrderDTO order);

    List<OrderResponseDTO> getActiveOrders();

    OrderResponseDTO getOrderById(String orderId);

    SimpleResponse updateOrderItemStatus(String orderId, String itemId, String status);

    SimpleResponse updateOrderStatus(String orderId, String status);

    String createOrderFromMessage(String queryResult);
}
