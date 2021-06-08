import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hibernate.validator.testutil.ConstraintViolationAssert.assertThat;
import static org.hibernate.validator.testutil.ConstraintViolationAssert.violationOf;

public class DriverTest {

    private Validator validator;
    private Driver driver;

    @BeforeClass
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        driver = new Driver();
        driver.setAge(10);
        driver.setHasDrivingLicense(false);
    }

    @Test
    public void defaultValidate() {
        Set<ConstraintViolation<Driver>> violations = validator.validate(driver);

        assertThat(violations).containsOnlyViolations(
                violationOf(NotNull.class)
        );
    }

    @Test
    public void groupValidate() {
        Set<ConstraintViolation<Driver>> violations = validator.validate(driver, DriverChecks.class);

        assertThat(violations).containsOnlyViolations(
                violationOf(NotNull.class),
                violationOf(Min.class),
                violationOf(AssertTrue.class)
        );
    }
}