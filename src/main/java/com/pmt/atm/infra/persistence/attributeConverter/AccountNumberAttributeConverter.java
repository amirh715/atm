package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.AccountNumber;
import javax.persistence.AttributeConverter;

public class AccountNumberAttributeConverter implements AttributeConverter<AccountNumber, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountNumber accountNumber) {
        return accountNumber.getValue();
    }

    @Override
    public AccountNumber convertToEntityAttribute(Integer accountNumber) {
        return AccountNumber.create(accountNumber);
    }

}
