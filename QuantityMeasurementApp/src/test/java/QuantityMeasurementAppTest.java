import com.apps.quantitymeasurement.Length;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        AssertJUnit.assertTrue(l1.equals(l2));
    }

    @Test
    public void testInchesEquality() {
        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(1.0, Length.LengthUnit.INCHES);

        AssertJUnit.assertTrue(i1.equals(i2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        AssertJUnit.assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        AssertJUnit.assertFalse(l1.equals(l2));
    }

    @Test
    public void testInchesInequality() {
        Length i1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length i2 = new Length(2.0, Length.LengthUnit.INCHES);

        AssertJUnit.assertFalse(i1.equals(i2));
    }

    @Test
    public void testCrossUnitInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(10.0, Length.LengthUnit.INCHES);

        AssertJUnit.assertFalse(l1.equals(l2));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length l1 = new Length(2.0, Length.LengthUnit.FEET);
        Length l2 = new Length(24.0, Length.LengthUnit.INCHES);

        AssertJUnit.assertTrue(l1.equals(l2));
    }

    @Test
    public void testSameReference() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);

        AssertJUnit.assertTrue(l1.equals(l1));
    }

    @Test
    public void testNullComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);

        AssertJUnit.assertFalse(l1.equals(null));
    }

    @Test
    public void testNullUnit() {
        Assert.assertThrows(IllegalArgumentException.class, () ->
                new Length(1.0, null));
    }
}