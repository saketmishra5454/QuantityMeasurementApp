package com.apps.quantitymeasurement;

public class Length {

    // Instance variables
    private final double value;
    private final LengthUnit unit;

    // Enum for supported units (Base unit = INCHES)
    public enum LengthUnit {

        FEET(12.0),          // 1 foot = 12 inches
        INCHES(1.0),         // Base unit
        YARDS(36.0),         // 1 yard = 36 inches
        CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Constructor
    public Length(double value, LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    // Convert to base unit (INCHES)
    private double convertToBaseUnit() {

        double result = this.value * this.unit.getConversionFactor();

        // Round to 5 decimal places for precision safety
        return Math.round(result * 100000.0) / 100000.0;
    }

    // Compare two Length objects
    public boolean compare(Length thatLength) {

        if (thatLength == null) return false;

        double diff = Math.abs(
                this.convertToBaseUnit() -
                        thatLength.convertToBaseUnit()
        );

        return diff < 0.00001;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null) return false;

        if (getClass() != o.getClass()) return false;

        Length other = (Length) o;

        return compare(other);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }

    // Getters
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }
}