package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.LastName;
import javax.persistence.AttributeConverter;

public class LastNameAttributeConverter implements AttributeConverter<LastName, String> {

    @Override
    public String convertToDatabaseColumn(LastName lastName) {
        return lastName != null ? lastName.getValue() : null;
    }

    @Override
    public LastName convertToEntityAttribute(String lastName) {
        return LastName.create(lastName);
    }

}
