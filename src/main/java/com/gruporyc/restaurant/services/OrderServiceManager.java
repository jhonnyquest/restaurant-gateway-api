package com.gruporyc.restaurant.services;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;

/**
 * OrderServiceManager: Public interface to expose Order service implementation
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
public interface OrderServiceManager{
    SimpleResponse createOrder(OrderDTO order);
}
