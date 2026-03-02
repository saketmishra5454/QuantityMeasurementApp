package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ======================================================
    // UC1 / UC4 : Equality Tests
    // ======================================================

    @Test
    public void testEquality_SameUnit() {
        assertTrue(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testEquality_DifferentSameUnit() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(2.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testEquality_FeetToInches() {
        assertTrue(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(12.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testEquality_YardToFeet() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(3.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testEquality_YardToInches() {
        assertTrue(new Length(1.0, Length.LengthUnit.YARDS)
                .equals(new Length(36.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testEquality_CentimeterToFoot() {
        assertTrue(new Length(30.48, Length.LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, Length.LengthUnit.FEET)));
    }

    @Test
    public void testEquality_CrossUnitInequality() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals(new Length(10.0, Length.LengthUnit.INCHES)));
    }

    @Test
    public void testEquality_NullComparison() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals(null));
    }

    @Test
    public void testEquality_ReferenceEquality() {
        Length l = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(l.equals(l));
    }

    @Test
    public void testEquality_ReflexiveSymmetricTransitive() {

        Length a = new Length(1.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);
        Length c = new Length(36.0, Length.LengthUnit.INCHES);

        // Reflexive
        assertTrue(a.equals(a));

        // Symmetric
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // Transitive
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testEquality_UsingDemonstrateMethod() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(QuantityMeasurementApp
                .demonstrateLengthEquality(l1, l2));
    }

    // ======================================================
    // UC5 : Conversion Tests
    // ======================================================

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0,
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(72.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54,
                        Length.LengthUnit.CENTIMETERS,
                        Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_FeetToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(6.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    public void testConversion_SameUnit() {
        assertEquals(5.0,
                QuantityMeasurementApp.convert(5.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testConversion_Zero() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_Negative() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testConversion_RoundTripPreservesValue() {

        double original = 5.0;

        double converted =
                QuantityMeasurementApp.convert(
                        QuantityMeasurementApp.convert(original,
                                Length.LengthUnit.FEET,
                                Length.LengthUnit.YARDS),
                        Length.LengthUnit.YARDS,
                        Length.LengthUnit.FEET);

        assertEquals(original, converted, EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NullUnit_Throws() {
        QuantityMeasurementApp.convert(1.0,
                null,
                Length.LengthUnit.INCHES);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NaN_Throws() {
        QuantityMeasurementApp.convert(Double.NaN,
                Length.LengthUnit.FEET,
                Length.LengthUnit.INCHES);
    }
}