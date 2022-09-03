package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.Password;
import javax.persistence.AttributeConverter;

public class PasswordAttributeConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password password) {
        return password.getValue();
    }

    @Override
    public Password convertToEntityAttribute(String password) {
        return Password.createFromHashedPassword(password);
    }

}
