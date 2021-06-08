import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@GroupSequence({DriverChecks.class, Driver.class})
public class Driver {
    @NotNull
    private String name;

    @Min(value = 18, groups = DriverChecks.class)
    private int age;

    @AssertTrue(groups = DriverChecks.class)
    private boolean hasDrivingLicense;
}