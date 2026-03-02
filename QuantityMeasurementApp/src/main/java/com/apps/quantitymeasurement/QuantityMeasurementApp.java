//package com.apps.quantitymeasurement;
//
//public class QuantityMeasurementApp {
//
//    // Generic method to demonstrate equality
//    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
//        return length1.equals(length2);
//    }
//
//    // Method with direct parameters
//    public static boolean demonstrateLengthComparison(
//            double value1, Length.LengthUnit unit1,
//            double value2, Length.LengthUnit unit2) {
//
//        Length length1 = new Length(value1, unit1);
//        Length length2 = new Length(value2, unit2);
//
//        return length1.equals(length2);
//    }
//
//    public static void main(String[] args) {
//
//        // Feet and Inches comparison
//        System.out.println("1 Foot == 12 Inches? " +
//                demonstrateLengthComparison(1.0,
//                        Length.LengthUnit.FEET,
//                        12.0,
//                        Length.LengthUnit.INCHES));
//
//        // Yards and Inches comparison
//        System.out.println("1 Yard == 36 Inches? " +
//                demonstrateLengthComparison(1.0,
//                        Length.LengthUnit.YARDS,
//                        36.0,
//                        Length.LengthUnit.INCHES));
//
//        // Centimeters and Inches comparison
//        System.out.println("100 CM == 39.3701 Inches? " +
//                demonstrateLengthComparison(100.0,
//                        Length.LengthUnit.CENTIMETERS,
//                        39.3701,
//                        Length.LengthUnit.INCHES));
//
//        // Feet and Yards comparison
//        System.out.println("3 Feet == 1 Yard? " +
//                demonstrateLengthComparison(3.0,
//                        Length.LengthUnit.FEET,
//                        1.0,
//                        Length.LengthUnit.YARDS));
//
//        // CM and Feet comparison
//        System.out.println("30.48 CM == 1 Foot? " +
//                demonstrateLengthComparison(30.48,
//                        Length.LengthUnit.CENTIMETERS,
//                        1.0,
//                        Length.LengthUnit.FEET));
//    }
//}

package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    private static final double EPSILON = 1e-6;

    /**
     * Static convert API
     */
    public static double convert(double value,
                                 Length.LengthUnit source,
                                 Length.LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null.");
        }

        double baseValue = value * source.getConversionFactor();
        return baseValue / target.getConversionFactor();
    }

    /**
     * Overloaded conversion method (raw value)
     */
    public static Length demonstrateLengthConversion(double value,
                                                     Length.LengthUnit from,
                                                     Length.LengthUnit to) {
        double converted = convert(value, from, to);
        return new Length(converted, to);
    }

    /**
     * Overloaded conversion method (Length object)
     */
    public static Length demonstrateLengthConversion(Length length,
                                                     Length.LengthUnit targetUnit) {
        return length.convertTo(targetUnit);
    }

    /**
     * Equality demonstration
     */
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    /**
     * Comparison demo using raw values
     */
    public static boolean demonstrateLengthComparison(double v1,
                                                      Length.LengthUnit u1,
                                                      double v2,
                                                      Length.LengthUnit u2) {

        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        return l1.equals(l2);
    }
}
