package junit5tests;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import calculator.UnitsConverter;
import org.junit.jupiter.api.*;

public class TestUnitConverter {

    private  final UnitsConverter converter = new UnitsConverter();

    @Test
    public void TestUnitConversion() throws IOException {
        assertEquals(
                110.23,
                converter.convertUnit(50, "kg", "lb"),
                0.01
        );
        assertEquals(
                590.55,
                converter.convertUnit(15, "m", "in"),
                0.01
        );

    }

}
