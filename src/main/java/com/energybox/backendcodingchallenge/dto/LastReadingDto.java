package com.energybox.backendcodingchallenge.dto;

import com.energybox.backendcodingchallenge.domain.LastReading;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class LastReadingDto {
    double value;
    String key;
    LocalDateTime timestamp;

    public static LastReadingDto fromEntity(LastReading lastReading){
        LastReadingDto dto = new LastReadingDto();
        dto.setKey(lastReading.getKey());
        dto.setValue(lastReading.getValue());
        dto.setTimestamp(lastReading.getTimestamp());

        return dto;
    }
}
