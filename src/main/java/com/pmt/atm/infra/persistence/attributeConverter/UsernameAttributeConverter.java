package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.Username;
import javax.persistence.AttributeConverter;

public class UsernameAttributeConverter implements AttributeConverter<Username, String> {

    @Override
    public String convertToDatabaseColumn(Username username) {
        return username.getValue();
    }

    @Override
    public Username convertToEntityAttribute(String username) {
        return Username.create(username);
    }

}
