import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.hibernate.validator.testutil.ConstraintViolationAssert.assertNoViolations;
import static org.hibernate.validator.testutil.ConstraintViolationAssert.assertThat;
import static org.hibernate.validator.testutil.ConstraintViolationAssert.violationOf;

public class CarTest {

    private Validator validator;

    @BeforeMethod
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testAllInvalid() {
        Car car = new Car(null, "");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertThat(violations).containsOnlyViolations(
                violationOf(NotNull.class).withMessage("不得是空值").withProperty("manufacturer")
        );
    }

    @Test
    public void testNonNullManufacturer() {
        Car car = new Car("Toyota", "ab123");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertNoViolations(violations);
    }
}