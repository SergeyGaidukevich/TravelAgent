package com.gaidukevich.tragent.entity.tour_type;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;


@Convert
public class TourTypeConverter implements AttributeConverter<TourType, String> {

    @Override
    public String convertToDatabaseColumn(TourType tourType) {
        return tourType.toString();
    }

    @Override
    public TourType convertToEntityAttribute(String s) {
        return TourType.valueOf(s.toUpperCase());
    }
}
