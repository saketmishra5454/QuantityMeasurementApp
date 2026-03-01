package com.apps.quantitymeasurement;


public class Length {

    private final double value;
    private final LengthUnit unit;

    // ENUM
    public enum LengthUnit {

        FEET(12.0),      // 1 foot = 12 inches
        INCHES(1.0);     // Base unit

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

    // Convert to base unit (inches)
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    // Compare two Length objects
    public boolean compare(Length other) {
        if (other == null) return false;

        return Double.compare(this.convertToBaseUnit(),
                other.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Length other = (Length) obj;

        return compare(other);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(convertToBaseUnit());
    }
}


