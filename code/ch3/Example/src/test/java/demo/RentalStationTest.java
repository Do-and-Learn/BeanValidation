package demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.testng.Assert.assertNotNull;

@SpringBootTest
public class RentalStationTest {

    @Autowired
    private RentalStation rentalStation;

    @Test
    public void testXXX() {
        assertNotNull(rentalStation);
        rentalStation.foo(null);
//        rentalStation.foo(null);
    }
}
