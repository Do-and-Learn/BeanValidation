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
        Car car = new Car(null, "x", 1, 400.5, BigDecimal.valueOf(100001));
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertThat(violations).containsOnlyViolations(
                violationOf(NotNull.class).withMessage("不得是空值").withProperty("manufacturer"),
                violationOf(Min.class).withMessage("There must be at least 2 seats").withProperty("seatCount"),
                violationOf(DecimalMax.class).withMessage("最高速度 400.50 不能超過 350").withProperty("topSpeed"),
                violationOf(DecimalMax.class).withMessage("價格不能高於 $100000").withProperty("price"),
                violationOf(Size.class).withMessage("車牌 'x' 長度必須介於 2 與 14 長度之間").withProperty("licensePlate")
        );
    }

    @Test
    public void testNonNullManufacturer() {
        Car car = new Car("Toyota", "abc123", 2, 350, BigDecimal.valueOf(100000));
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertNoViolations(violations);
    }
}