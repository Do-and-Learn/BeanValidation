import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    @NotNull
    private String manufacturer;

    @Size(min = 2, max = 14)
    private String licensePlate;
}