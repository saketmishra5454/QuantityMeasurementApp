

//package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.QuantityMeasurementApp;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class QuantityMeasurementAppTest {

    // -------------------- FEET TEST CASES --------------------

    @Test
    public void testFeetEquality_SameValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f2), "1.0 ft should be equal to 1.0 ft");
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        QuantityMeasurementApp.Feet f2 = new QuantityMeasurementApp.Feet(2.0);

        assertFalse(f1.equals(f2), "1.0 ft should not be equal to 2.0 ft");
    }

    @Test
    public void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertFalse(f1.equals(null), "Feet object should not be equal to null");
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);
        String other = "1.0";

        assertFalse(f1.equals(other), "Feet object should not be equal to different class object");
    }

    @Test
    public void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet f1 = new QuantityMeasurementApp.Feet(1.0);

        assertTrue(f1.equals(f1), "Object should be equal to itself (reflexive property)");
    }

    // -------------------- INCHES TEST CASES --------------------

    @Test
    public void testInchesEquality_SameValue() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(1.0);

        assertTrue(i1.equals(i2), "1.0 inch should be equal to 1.0 inch");
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
        QuantityMeasurementApp.Inches i2 = new QuantityMeasurementApp.Inches(2.0);

        assertFalse(i1.equals(i2), "1.0 inch should not be equal to 2.0 inch");
    }

    @Test
    public void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);

        assertFalse(i1.equals(null), "Inches object should not be equal to null");
    }

    @Test
    public void testInchesEquality_DifferentClass() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);
        Integer other = 1;

        assertFalse(i1.equals(other), "Inches object should not be equal to different class object");
    }

    @Test
    public void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);

        assertTrue(i1.equals(i1), "Object should be equal to itself (reflexive property)");
    }
}