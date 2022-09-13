package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.AccountNumber;
import javax.persistence.AttributeConverter;
import javax.xml.bind.ValidationException;

public class AccountNumberAttributeConverter implements AttributeConverter<AccountNumber, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountNumber accountNumber) {
        return accountNumber.getValue();
    }

    @Override
    public AccountNumber convertToEntityAttribute(Integer accountNumber) {
        try {
            return AccountNumber.create(accountNumber);
        } catch (ValidationException e) {
            return null;
        }
    }

}
