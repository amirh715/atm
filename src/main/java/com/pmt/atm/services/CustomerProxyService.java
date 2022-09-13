package com.pmt.atm.services;

import com.pmt.atm.infra.DTOs.CustomerDetailsResponse;
import com.pmt.atm.infra.exceptions.InterServiceCommunicationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class CustomerProxyService {

    private static final String BASE_PATH = "http://customer-service/customer/";

    public CustomerDetailsResponse getCustomerDetailsById(String customerId) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final ResponseEntity<CustomerDetailsResponse> response =
                    restTemplate.getForEntity(BASE_PATH + "details?customerId=" + customerId, CustomerDetailsResponse.class);
            return response.getBody();
        } catch(RuntimeException exception) {
            throw new InterServiceCommunicationException(exception);
        }
    }

}
