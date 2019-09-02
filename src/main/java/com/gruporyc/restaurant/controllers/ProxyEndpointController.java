package com.gruporyc.restaurant.controllers;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.services.OrderServiceManager;
import com.gruporyc.restaurant.utilities.TextsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


/**
 * ProxyEndpointController: Proxy controllers to manage all interactions from customer applications
 * to BE platform
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
@RestController
@RequestMapping("/v1")
public class ProxyEndpointController {

    @Autowired
    private OrderServiceManager orderService;
    @Autowired
    private TextsHelper textsHelper;

    @RequestMapping("/")
    public String index() {
        return "Restaurant gateway service working properly!";
    }

    /**
     * createOrder: Method to get customer order information and create order into platform
     * @author jmunoz
     * @since 07/08/2019
     * @param newOrder New OrderDTO to be created
     * @see ResponseEntity<Object>
     */
    @RequestMapping("order")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDTO newOrder) {
        System.out.println("ORDEN: " + newOrder.toString());
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(orderService.createOrder(newOrder));
        } catch (HttpClientErrorException ex) {
            responseEntity = setErrorResponse(ex);
        }
        return responseEntity;
    }

    /**
     * getActiveOrders: Method to get active orders into platform
     * @author jmunoz
     * @since 13/08/2019
     * @see ResponseEntity<Object>
     */
    @RequestMapping(value = "orders/active", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getActiveOrders() {

        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(orderService.getActiveOrders());
        } catch (HttpClientErrorException ex) {
            responseEntity = setErrorResponse(ex);
        }
        return responseEntity;
    }

    /**
     * updateOrderStatus: Method to update given order by id
     * @author jmunoz
     * @since 10/08/2019
     * @param orderId Order universal identifier
     * @param payload Request payload - status to be updated
     * @see ResponseEntity <Object>
     */
    @RequestMapping(value = "orders/{orderId}/status", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> updateOrderStatus(@PathVariable("orderId") String orderId,
                                                    @RequestBody ModelMap payload) {

        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(
                    orderService.updateOrderStatus(orderId, payload.get("status").toString()));
        } catch (HttpClientErrorException ex) {
            responseEntity = setErrorResponse(ex);
        }
        return responseEntity;
    }

    /**
     * getOrderById: Method to get a single order given ID
     * @author jmunoz
     * @since 13/08/2019
     * @see ResponseEntity<Object>
     */
    @RequestMapping(value = "orders/{order_id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> updateOrderStatus(@PathVariable("order_id") String orderId) {

        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(orderService.getOrderById(orderId));
        } catch (HttpClientErrorException ex) {
            responseEntity = setErrorResponse(ex);
        }
        return responseEntity;
    }

    /**
     * updateOrderItemStatus: Method to update item status from a given order
     * @author jmunoz
     * @param orderId Order universal identifier
     * @param itemId Order universal identifier
     * @param payload Request payload - status to be updated
     * @since 13/08/2019
     * @see ResponseEntity<Object>
     */
    @RequestMapping(value = "orders/{order_id}/item/{item_id}/status", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Object> updateOrderItemStatus(@PathVariable("order_id") String orderId,
                                                        @PathVariable("item_id") String itemId,
                                                        @RequestBody ModelMap payload) {

        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(orderService.updateOrderItemStatus(orderId, itemId,
                    payload.get("status").toString()));
        } catch (HttpClientErrorException ex) {
            responseEntity = setErrorResponse(ex);
        }
        return responseEntity;
    }

    private ResponseEntity<Object> setErrorResponse(HttpClientErrorException ex) {
        String message = "";
        HttpStatus status;
        switch (ex.getStatusCode().value()) {
            case 404:
                message = textsHelper.getTranslation("api.error.notFound");
                status = HttpStatus.NOT_FOUND;
                break;
            case 401:
                textsHelper.getTranslation("api.error.accessDenied");
                status = HttpStatus.UNAUTHORIZED;
                break;
            case 400:
                textsHelper.getTranslation("api.error.badRequest");
                status = HttpStatus.BAD_REQUEST;
                break;
            case 409:
                message = textsHelper.getTranslation("api.error.alreadyExist");
                status = HttpStatus.NOT_ACCEPTABLE;
                break;
            case 500:
                textsHelper.getTranslation("api.error.internalServer");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case 503:
                textsHelper.getTranslation("api.error.serviceUnavailable");
                status = HttpStatus.SERVICE_UNAVAILABLE;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                textsHelper.getTranslation("api.error.unknownError");
        }
        return  ResponseEntity.status(status)
                .body(new SimpleResponse(false, message, String.valueOf(ex.getStatusCode().value())));

    }
}