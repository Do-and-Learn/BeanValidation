import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class CarTest {

    private Validator validator;

    @BeforeMethod
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNullManufacturer() {
        Car car = new Car();
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(violations.size(), 1);

        Iterator<ConstraintViolation<Car>> iterator = violations.iterator();
        ConstraintViolation<Car> violation = iterator.next();
        assertEquals(violation.getMessage(), "製造商名稱不得為 null");
    }

    @Test
    public void testNonNullManufacturer() {
        Car car = new Car();
        car.setManufacturer("Toyota");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(violations.size(), 0);
    }
}