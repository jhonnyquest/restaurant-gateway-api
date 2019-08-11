package com.gruporyc.restaurant.services.implementations;

import com.gruporyc.restaurant.dto.CustomerDTO;
import com.gruporyc.restaurant.dto.SimpleResponse;
import com.gruporyc.restaurant.services.CustomerApiManager;
import com.gruporyc.restaurant.utilities.MockHelper;
import com.gruporyc.restaurant.utilities.TextsHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**TODO: remove mock feature when service call and logic is done*/
    @Override
    public List<CustomerDTO> getCustomers() {
        return null;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        if(id == 0) {
            LOGGER.info(textsHelper.getTranslation("api.customer.notExist.message"));
            return null;
        }
        return MockHelper.getCustomerById(id);
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        return null;
    }

    @Override
    public SimpleResponse createCustomer(CustomerDTO customer) {
        LOGGER.info(textsHelper.getTranslation("api.customer.created.message"));
        return new SimpleResponse(true, MockHelper.getRandomId().toString(), HttpStatus.CREATED.name());
    }

    @Override
    public SimpleResponse updateCustomer(CustomerDTO customer) {
        return null;
    }
}
