package com.gruporyc.restaurant.controllers;

import com.gruporyc.restaurant.dto.OrderDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.services.OrderServiceManager;
import com.gruporyc.restaurant.utilities.TextsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<Object> createOrder(@Validated @RequestBody OrderDTO newOrder) {

        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = ResponseEntity.ok(orderService.createOrder(newOrder));
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
                .body(new SimpleResponse(false, message));

    }
}