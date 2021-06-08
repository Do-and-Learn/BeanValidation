import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Car {
    @NotNull(message = "製造商名稱不得為 null")
    private String manufacturer;
}
