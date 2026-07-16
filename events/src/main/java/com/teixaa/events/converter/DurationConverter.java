package com.teixaa.events.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        if (duration == null) {
            return null;
        }
        return duration.toMinutes();
    }

    @Override
    public Duration convertToEntityAttribute(Long minutes) {
        if (minutes == null) {
            return null;
        }
        return Duration.ofMinutes(minutes);
    }
}
