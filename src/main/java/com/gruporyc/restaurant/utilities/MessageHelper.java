package com.gruporyc.restaurant.utilities;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.OrderItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class MessageHelper {

    /** TODO: Update this component to retrieve this parameter from properties*/
    private static String RESTAURANT_CUSTOMER_ID = "4a135a36-cf5c-4041-bc9c-6fd2e352d6b6";

    public static OrderDTO parseOrder (String message){
        message = message.replaceAll("\\s+", " ").trim().toUpperCase();
        String[] values = message.split(" ");
        if(values.length < 2) {
            return null;
        }

        OrderDTO order = new OrderDTO();
        //order.getCustomer().setId(RESTAURANT_CUSTOMER_ID);
        order.setCustomerId(RESTAURANT_CUSTOMER_ID);
        order.setTable(values[0]);
        order.setItems(new ArrayList<OrderItemDTO>() {{
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setId(values[1]);
            orderItemDTO.setQuantity((Objects.isNull(values[2])) ? 1 : Long.valueOf(values[2]));
            add(orderItemDTO);
        }});
        return order;
    }
}
