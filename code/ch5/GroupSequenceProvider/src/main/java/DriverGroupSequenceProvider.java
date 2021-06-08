import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class DriverGroupSequenceProvider implements DefaultGroupSequenceProvider<Driver> {
    @Override
    public List<Class<?>> getValidationGroups(Driver driver) {
        List<Class<?>> defaultGroupSequence = new ArrayList<>();
        defaultGroupSequence.add(DriverChecks.class);
        defaultGroupSequence.add(Driver.class);
        return defaultGroupSequence;
    }
}
