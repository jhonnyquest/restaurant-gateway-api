package com.gruporyc.restaurant.services.implementations;

import com.gruporyc.restaurant.dto.*;
import com.gruporyc.restaurant.services.CustomerApiManager;
import com.gruporyc.restaurant.services.OrderApiManager;
import com.gruporyc.restaurant.services.OrderServiceManager;
import com.gruporyc.restaurant.utilities.TextsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;

import static com.gruporyc.restaurant.utilities.MessageHelper.parseOrder;

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
    @Autowired
    private TextsHelper textsHelper;

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
        CustomerDTO customer = customerApi.getCustomerByEmail(order.getCustomer().getEmail());

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

        System.out.println("item: " + orderById.toString());
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

    @Override
    public String createOrderFromMessage(String queryResult) {
        OrderDTO order = parseOrder(queryResult);
        if(Objects.isNull(order)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        SimpleResponse response = orderApi.createOrder(order);
        if(response.isSuccess()) {
            return textsHelper.getTranslation("api.order.created.message");
        } else {
            return textsHelper.getTranslation("api.order.notCreated.message");
        }
    }
}
