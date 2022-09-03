package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.FirstName;
import javax.persistence.AttributeConverter;

public class FirstNameAttributeConverter implements AttributeConverter<FirstName, String> {

    @Override
    public String convertToDatabaseColumn(FirstName firstName) {
        return firstName != null ? firstName.getValue() : null;
    }

    @Override
    public FirstName convertToEntityAttribute(String firstName) {
        return FirstName.create(firstName);
    }

}
