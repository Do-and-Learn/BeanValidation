import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

@Data
@GroupSequenceProvider(DriverGroupSequenceProvider.class)
public class Driver {
    @NotNull
    private String name;

    @Min(value = 18, groups = DriverChecks.class)
    private int age;

    @AssertTrue(groups = DriverChecks.class)
    private boolean hasDrivingLicense;
}