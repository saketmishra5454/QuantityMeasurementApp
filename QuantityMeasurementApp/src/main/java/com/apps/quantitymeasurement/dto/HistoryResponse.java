package com.apps.quantitymeasurement.dto;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.time.LocalDateTime;

public record HistoryResponse(
        Long id,
        Double thisValue,
        String thisUnit,
        String thisMeasurementType,
        Double thatValue,
        String thatUnit,
        String thatMeasurementType,
        String operation,
        Double resultValue,
        String resultUnit,
        String resultMeasurementType,
        String resultString,
        boolean isError,
        String errorMessage,
        LocalDateTime createdAt
) {
    public static HistoryResponse fromEntity(QuantityMeasurementEntity entity) {
        return new HistoryResponse(
                entity.getId(),
                entity.getThisValue(),
                entity.getThisUnit(),
                entity.getThisMeasurementType(),
                entity.getThatValue(),
                entity.getThatUnit(),
                entity.getThatMeasurementType(),
                entity.getOperation(),
                entity.getResultValue(),
                entity.getResultUnit(),
                entity.getResultMeasurementType(),
                entity.getResultString(),
                entity.isError(),
                entity.getErrorMessage(),
                entity.getCreatedAt()
        );
    }
}
