package com.pmt.atm.domain.CreditCalculation;

import com.pmt.atm.domain.CreditRate;
import com.pmt.atm.domain.Toman;
import com.pmt.atm.domain.Transaction;

import java.util.Set;

public interface CustomerCreditCalculationPolicy {

    CreditRate calculateCustomerCredit(Set<Transaction> transactions, Toman averageTransactionAmount);

}
