package com.gruporyc.restaurant.services;

import com.gruporyc.restaurant.dto.CustomerDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;

import java.util.List;

/**
 * CustomerApiManager: Public interface to expose Customer API manager implementation
 * @author jmunoz
 * @since 07/08/2019
 * @version 1.0.0
 */
public interface CustomerApiManager {
    /**TODO: Implement paging feature for list responses */
    List<CustomerDTO> getCustomers();
    CustomerDTO getCustomerById(Long id);
    CustomerDTO getCustomerByEmail(String email);
    SimpleResponse createCustomer(CustomerDTO customer);
    SimpleResponse updateCustomer(CustomerDTO customer);
}
