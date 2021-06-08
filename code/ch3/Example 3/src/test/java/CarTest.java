import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.executable.ExecutableValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Set;

import static org.hibernate.validator.testutil.ConstraintViolationAssert.*;

public class CarTest {

    private ExecutableValidator executableValidator;

    @BeforeMethod
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        executableValidator = factory.getValidator().forExecutables();
    }

    @Test
    public void testInvalidConstructor() throws NoSuchMethodException {
        Set<ConstraintViolation<Car>> violations = executableValidator.validateConstructorParameters(Car.class.getConstructor(String.class), new Object[]{null});
        assertThat(violations).containsOnlyViolations(
                violationOf(NotNull.class).withPropertyPath(pathWith().constructor(Car.class).parameter("arg0", 0))
        );
    }

    @Test
    public void testValidConstructor() throws NoSuchMethodException {
        Set<ConstraintViolation<Car>> violations = executableValidator.validateConstructorParameters(Car.class.getConstructor(String.class), new Object[]{"Toyota"});
        assertNoViolations(violations);
    }

    @Test
    public void invalidParameter() throws NoSuchMethodException {
        Car car = new Car("Toyota");

        Set<ConstraintViolation<Car>> violations = executableValidator.validateParameters(
                car, Car.class.getDeclaredMethod("drive", int.class), new Object[]{100}
        );

        assertThat(violations).containsOnlyViolations(
                violationOf(Max.class).withPropertyPath(pathWith().method("drive").parameter("arg0", 0))
        );
    }

    @Test
    public void validParameter() throws NoSuchMethodException {
        Car car = new Car("Toyota");

        Set<ConstraintViolation<Car>> violations = executableValidator.validateParameters(
                car, Car.class.getDeclaredMethod("drive", int.class), new Object[]{75}
        );

        assertNoViolations(violations);
    }

    @Test
    public void invalidReturnValue() throws NoSuchMethodException {
        Car car = new Car("Toyota");
        Set<ConstraintViolation<Car>> violations = executableValidator.validateReturnValue(
                car, Car.class.getDeclaredMethod("getPassengers"), car.getPassengers()
        );

        assertThat(violations).containsOnlyViolations(
                violationOf(Size.class).withPropertyPath(pathWith().method("getPassengers").returnValue())
        );
    }

    @Test
    public void validReturnValue() throws NoSuchMethodException {
        Car car = new Car("Toyota");
        Set<ConstraintViolation<Car>> violations = executableValidator.validateReturnValue(
                car, Car.class.getDeclaredMethod("getPassengers"), Collections.singleton(new Object())
        );

        assertNoViolations(violations);
    }

}