package com.apps.quantitymeasurement;

public class Length {

    // Instance variables
    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

    // Enum for supported units (Base unit = INCHES)
    public enum LengthUnit {

        FEET(12.0),          // 1 foot = 12 inches
        INCHES(1.0),         // Base unit
        YARDS(36.0),         // 1 yard = 36 inches
        CENTIMETERS(1.0 / 2.54); // 1 cm = 0.393701 inches

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

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        this.value = value;
        this.unit = unit;
    }

    // Getters
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Convert to base unit (INCHES)
    private double convertToBaseUnit() {

        double result = this.value * this.unit.getConversionFactor();

        // Round to 5 decimal places for precision safety
        return Math.round(result * 1000000.0) / 1000000.0;
    }

    // Compare two Length objects
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }

        double baseValue = convertToBaseUnit();
        double convertedValue = baseValue / targetUnit.getConversionFactor();

        return new Length(convertedValue, targetUnit);
    }


     // Private helper comparison method.

    private boolean compare(Length other) {
        return Math.abs(
                this.convertToBaseUnit() -
                        other.convertToBaseUnit()
        ) < EPSILON;
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

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}

