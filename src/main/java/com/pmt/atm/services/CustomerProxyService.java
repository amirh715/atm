package com.pmt.atm.services;

import com.pmt.atm.infra.DTOs.CustomerDetails;
import com.pmt.atm.infra.exceptions.InterServiceCommunicationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class CustomerProxyService {

    private static final String BASE_PATH = "http://localhost:8080/customer/";

    public CustomerDetails getCustomerDetailsById(String customerId) {
        try {
            final RestTemplate restTemplate = new RestTemplate();
            final String destination = BASE_PATH + "details?customerId=" + customerId;
            final ResponseEntity<CustomerDetails> response =
                    restTemplate.getForEntity(destination, CustomerDetails.class);
            return response.getBody();
        } catch(RuntimeException exception) {
            throw new InterServiceCommunicationException(exception);
        }
    }

}
