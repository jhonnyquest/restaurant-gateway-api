package com.gruporyc.restaurant.services;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.OrderResponseDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;

import java.util.List;

/**
 * OrderApiManager: Public interface to expose Order API manager implementation
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
public interface OrderApiManager {
    /**TODO: Implement paging feature for list responses */
    List<OrderDTO> getOrders();
    OrderResponseDTO getOrderById(String orderId);
    SimpleResponse createOrder(OrderDTO order);
    void deleteOrder(String orderId);
    List<OrderResponseDTO> getActiveOrders();

    SimpleResponse updateOrderItemStatus(String orderId, String itemId, String status);

    SimpleResponse updateOrderStatus(String orderId, String status);
}
