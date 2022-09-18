package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.AccountNumber;
import javax.persistence.AttributeConverter;
import javax.xml.bind.ValidationException;

public class AccountNumberAttributeConverter implements AttributeConverter<AccountNumber, String> {

    @Override
    public String convertToDatabaseColumn(AccountNumber accountNumber) {
        return accountNumber.getValue();
    }

    @Override
    public AccountNumber convertToEntityAttribute(String accountNumber) {
        try {
            return AccountNumber.create(accountNumber);
        } catch (ValidationException e) {
            return null;
        }
    }

}
