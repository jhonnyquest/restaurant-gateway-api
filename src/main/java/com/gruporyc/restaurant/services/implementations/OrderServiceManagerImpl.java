package com.gruporyc.restaurant.services.implementations;

import com.gruporyc.restaurant.dto.CustomerDTO;
import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.OrderResponseDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.services.CustomerApiManager;
import com.gruporyc.restaurant.services.OrderApiManager;
import com.gruporyc.restaurant.services.OrderServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

/**
 * OrderServiceManagerImpl: Service that implements Order operations by using microservices business APIs
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
@Component
public class OrderServiceManagerImpl implements OrderServiceManager {

    @Autowired
    private CustomerApiManager customerApi;
    @Autowired
    private OrderApiManager orderApi;

    /**
     * createOrder: Method to create a new order properly, by using Customer and Order microservice APIs
     * @author jmunoz
     * @since 07/08/2019
     * @param order New order to be created
     * @see SimpleResponse
     */
    @Override
    public SimpleResponse createOrder(OrderDTO order) {
        if(Objects.nonNull(orderApi.getOrderById(order.getId()))) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
        CustomerDTO customer = customerApi.getCustomerById(order.getCustomer().getId());

        String customerId = (Objects.isNull(customer)) ?
                customerApi.createCustomer(order.getCustomer()).getMessage() :
                customer.getId();

        order.setCustomerId(customerId);

        return orderApi.createOrder(order);
    }

    /**
     * getActiveOrders: Method to get active orders, by using Customer and Order microservice APIs
     * @author jmunoz
     * @since 13/08/2019
     * @see SimpleResponse
     */
    @Override
    public List<OrderResponseDTO> getActiveOrders() {
        return orderApi.getActiveOrders();
    }

    @Override
    public OrderResponseDTO getOrderById(String orderId) {
        OrderResponseDTO orderById = orderApi.getOrderById(orderId);
        return orderById;
    }

    @Override
    public SimpleResponse updateOrderItemStatus(String orderId, String itemId, String status) {
        return orderApi.updateOrderItemStatus(orderId, itemId, status);
    }

    @Override
    public SimpleResponse updateOrderStatus(String orderId, String status) {
        return orderApi.updateOrderStatus(orderId, status);
    }
}
