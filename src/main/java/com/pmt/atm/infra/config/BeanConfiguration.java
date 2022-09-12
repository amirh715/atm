package com.pmt.atm.infra.config;

import com.pmt.atm.domain.CreditCalculation.BasicCustomerCreditCalculationPolicy;
import com.pmt.atm.domain.CreditCalculation.CustomerCreditCalculationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CustomerCreditCalculationPolicy customerCreditCalculationPolicy() {
        return new BasicCustomerCreditCalculationPolicy(
                1,
                3
        );
    }

}
