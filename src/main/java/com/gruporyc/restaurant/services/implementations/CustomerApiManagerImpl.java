package com.gruporyc.restaurant.services.implementations;

import com.gruporyc.restaurant.dto.CustomerDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.services.CustomerApiManager;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomerApiManagerImpl: Service that implements Customer operations by using Customer microservice business API
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
@Component
public class CustomerApiManagerImpl implements CustomerApiManager {

    private static final Logger LOGGER = LogManager.getLogger(CustomerApiManagerImpl.class);
    @Autowired
    private TextsHelper textsHelper;

    @Autowired
    private RestTemplateHelper rt;

    @Value("${api.restaurant.customer.endpoint}")
    private String customerEndpoint;

    /**TODO: remove mock feature when service call and logic is done*/
    @Override
    public List<CustomerDTO> getCustomers() {
        return null;
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        try{
            ResponseEntity<CustomerDTO> response = rt.processRequestGet(
                    customerEndpoint + "/" + id,null,CustomerDTO.class);
            return response.getBody();
        } catch(HttpClientErrorException ex) {
            if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;
        }
        return null;
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        try{
            ResponseEntity<CustomerDTO> response = rt.processRequestGet(
                    customerEndpoint + "/email/" + email,null,CustomerDTO.class);
            return response.getBody();
        } catch(HttpClientErrorException ex) {
            if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
                return null;
        }
        return null;
    }

    @Override
    public SimpleResponse createCustomer(CustomerDTO customer) {
        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("id", customer.getId());
        requestBody.put("first_name", customer.getNames());
        requestBody.put("last_name", customer.getLastNames());
        requestBody.put("address_1", customer.getAddress1());
        requestBody.put("address_2", customer.getAddress2());
        requestBody.put("city", customer.getCity());
        requestBody.put("state", customer.getState());
        requestBody.put("country", customer.getCountry());
        requestBody.put("email", customer.getEmail());
        requestBody.put("phone", customer.getPhone());

        ResponseEntity<SimpleResponse> response = rt.processRequestPost(
                customerEndpoint, requestBody, SimpleResponse.class);
        return (response.getStatusCode() == HttpStatus.CREATED) ? response.getBody() : null;
    }

    /**TODO: for production environment please implement this method*/
    @Override
    public SimpleResponse updateCustomer(CustomerDTO customer) {
        return null;
    }
}
