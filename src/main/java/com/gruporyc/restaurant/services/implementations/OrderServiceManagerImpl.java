package com.gruporyc.restaurant.services.implementations;

import com.gruporyc.restaurant.dto.CustomerDTO;
import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.services.CustomerApiManager;
import com.gruporyc.restaurant.services.OrderApiManager;
import com.gruporyc.restaurant.services.OrderServiceManager;
import com.gruporyc.restaurant.utilities.TextsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import sun.net.www.http.HttpClient;

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

        Long customerId = (Objects.isNull(customer)) ?
                Long.parseLong(customerApi.createCustomer(order.getCustomer()).getMessage()) :
                customer.getId();

        order.setCustomerId(customerId);

        return orderApi.createOrder(order);
    }
}
