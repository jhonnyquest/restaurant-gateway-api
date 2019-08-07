package com.gruporyc.restaurant.services.implementations;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.enums.Status;
import com.gruporyc.restaurant.services.OrderApiManager;
import com.gruporyc.restaurant.utilities.MockHelper;
import com.gruporyc.restaurant.utilities.TextsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public OrderDTO getOrderById(Long orderId) {
        /**TODO: remove mock feature when service call and logic is done*/
        if(orderId != 0) {
            LOGGER.info(textsHelper.getTranslation("api.order.exists.message"));
            return null;
        }
        return MockHelper.getOrderDtoByIdByStatus(orderId, Status.CREATED);
    }

    @Override
    public SimpleResponse createOrder(OrderDTO order) {
        LOGGER.info(textsHelper.getTranslation("api.order.created.message"));
        return new SimpleResponse(true, textsHelper.getTranslation("api.order.created.message"));
    }

    @Override
    public void deleteOrder(Long orderId) {

    }
}
