package com.pmt.atm.infra.persistence.attributeConverter;

import com.pmt.atm.domain.Toman;
import javax.persistence.AttributeConverter;

public class TomanAttributeConverter implements AttributeConverter<Toman, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Toman toman) {
        return toman != null ? toman.getValue() : null;
    }

    @Override
    public Toman convertToEntityAttribute(Integer money) {
        return Toman.create(money);
    }

}
