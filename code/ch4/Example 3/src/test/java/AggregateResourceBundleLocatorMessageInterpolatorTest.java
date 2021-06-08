import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hibernate.validator.testutil.ConstraintViolationAssert.assertNoViolations;
import static org.hibernate.validator.testutil.ConstraintViolationAssert.assertThat;
import static org.hibernate.validator.testutil.ConstraintViolationAssert.violationOf;

public class AggregateResourceBundleLocatorMessageInterpolatorTest {
    private Validator validator;

    @BeforeMethod
    public void setup() {
        ValidatorFactory factory = Validation.byDefaultProvider().configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator(
                        new AggregateResourceBundleLocator(List.of("MyMessages1", "MyMessages2"))))
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testAllInvalid() {
        Car car = new Car(null, "");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertThat(violations).containsOnlyViolations(
                violationOf(NotNull.class).withMessage("請提供資料").withProperty("manufacturer"),
                violationOf(Size.class).withMessage("大小必須在 2 和 14 之間").withProperty("licensePlate")
        );
    }

    @Test
    public void testNonNullManufacturer() {
        Car car = new Car("Toyota", "ab123");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertNoViolations(violations);
    }
}
