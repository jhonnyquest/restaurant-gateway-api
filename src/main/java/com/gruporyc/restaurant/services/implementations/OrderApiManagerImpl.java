package com.gruporyc.restaurant.services.implementations;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.OrderItemDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.services.OrderApiManager;
import com.gruporyc.restaurant.utilities.RestTemplateHelper;
import com.gruporyc.restaurant.utilities.TextsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomerApiManagerImpl: Service that implements Order operations by using Order microservice business API
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
@Component
public class OrderApiManagerImpl implements OrderApiManager {

    private static final Logger LOGGER = LogManager.getLogger(OrderApiManagerImpl.class);

    @Autowired
    private TextsHelper textsHelper;

    @Override
    public List<OrderDTO> getOrders() {
        return null;
    }

    @Autowired
    private RestTemplateHelper rt;

    @Value("${api.restaurant.order.endpoint}")
    private String orderEndpoint;

    @Override
    public OrderDTO getOrderById(String orderId) {
        try{
            ResponseEntity<OrderDTO> response = rt.processRequestGet(
                    orderEndpoint  + "/" + orderId,  null, OrderDTO.class);
            return response.getBody();
        } catch(HttpClientErrorException ex) {
            if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;
        }
        return null;
    }

    @Override
    public SimpleResponse createOrder(OrderDTO order) {
        Map<String, Object> requestBody = new HashMap<>();
        List<Map<String, String>> items = new ArrayList<>();

        for (OrderItemDTO orderItemDTO : order.getItems()) {
            Map<String, String> newItem = new HashMap<>();
            newItem.put("id", orderItemDTO.getId());
            newItem.put("name", orderItemDTO.getName());
            newItem.put("description", orderItemDTO.getDescription());
            newItem.put("quantity", orderItemDTO.getQuantity().toString());
            newItem.put("price", orderItemDTO.getPrice().toString());
            items.add(newItem);
        }

        requestBody.put("id", order.getId());
        requestBody.put("customer_id", order.getCustomerId());
        requestBody.put("items", items);

        ResponseEntity<SimpleResponse> response = rt.processRequestPostObject(
                orderEndpoint, requestBody, SimpleResponse.class);
        return (response.getStatusCode() == HttpStatus.CREATED) ? response.getBody() : null;
    }

    @Override
    public void deleteOrder(String orderId) {

    }
}
