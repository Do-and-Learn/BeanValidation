import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Collections;
import java.util.List;

public class Car {

    public Car(@NotNull String manufacturer) {
    }

    public void drive(@Max(75) int speedInMph) {
    }

    @Size(min = 1)
    public List<Object> getPassengers() {
        return Collections.emptyList();
    }
}