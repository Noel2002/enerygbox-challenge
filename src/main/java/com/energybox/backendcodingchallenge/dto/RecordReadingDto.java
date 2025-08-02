package com.energybox.backendcodingchallenge.dto;

import lombok.Data;

@Data
public final class RecordReadingDto {
    double value;
    String key;
}
