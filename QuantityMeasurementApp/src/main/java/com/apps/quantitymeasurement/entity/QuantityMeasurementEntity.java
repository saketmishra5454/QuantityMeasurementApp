package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.model.QuantityModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "quantity_measurement_entity", indexes = {
        @Index(name = "idx_operation", columnList = "operation"),
        @Index(name = "idx_measurement_type", columnList = "this_measurement_type"),
        @Index(name = "idx_created_at", columnList = "created_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Column(name = "this_value", nullable = false)
    private double thisValue;

    @Column(name = "this_unit", nullable = false)
    private String thisUnit;

    @Column(name = "this_measurement_type", nullable = false)
    private String thisMeasurementType;

    @Column(name = "that_value", nullable = false)
    private Double thatValue;

    @Column(name = "that_unit", nullable = false)
    private String thatUnit;

    @Column(name = "that_measurement_type", nullable = false)
    private String thatMeasurementType;

    @Column(name = "operation", nullable = false)
    private String operation;

    @Column(name = "result_value")
    private Double resultValue;

    @Column(name = "result_unit")
    private String resultUnit;

    @Column(name = "result_measurement_type")
    private String resultMeasurementType;

    @Column(name = "result_string")
    private String resultString;

    @Column(name = "is_error")
    private boolean isError;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity, String operation) {
        if (thisQuantity != null) {
            this.thisValue = thisQuantity.getValue();
            this.thisUnit = thisQuantity.getUnit().getUnitName();
            this.thisMeasurementType = thisQuantity.getUnit().getMeasurementType();
        }

        if (thatQuantity != null) {
            this.thatValue = thatQuantity.getValue();
            this.thatUnit = thatQuantity.getUnit().getUnitName();
            this.thatMeasurementType = thatQuantity.getUnit().getMeasurementType();
        }

        this.operation = operation;
    }

    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity, String operation, String resultString) {
        this(thisQuantity, thatQuantity, operation);
        this.resultString = resultString;
        this.isError = false;
    }

    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, String operation, QuantityModel<?> resultQuantity) {
        this(thisQuantity, null, operation);
        this.resultValue = resultQuantity.getValue();
        this.resultUnit = resultQuantity.getUnit().getUnitName();
        this.resultMeasurementType = resultQuantity.getUnit().getMeasurementType();
        this.isError = false;
    }

    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity, String operation, QuantityModel<?> resultQuantity) {
        this(thisQuantity, thatQuantity, operation);
        this.resultValue = resultQuantity.getValue();
        this.resultUnit = resultQuantity.getUnit().getUnitName();
        this.resultMeasurementType = resultQuantity.getUnit().getMeasurementType();
        this.isError = false;
    }

    public QuantityMeasurementEntity(QuantityModel<?> thisQuantity, QuantityModel<?> thatQuantity, String operation, String errorMessage, boolean isError) {
        this(thisQuantity, thatQuantity, operation);
        this.errorMessage = errorMessage;
        this.isError = isError;
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
