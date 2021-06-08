import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Car {
    @NotNull
    private String manufacturer;

    @Size(min = 2, max = 14, message = "車牌 '${validatedValue}' 長度必須介於 {min} 與 {max} 長度之間")
    private String licensePlate;

    @Min(value = 2, message = "There must be at least {value} seat${value > 1 ? 's' : ''}")
    private int seatCount;

    @DecimalMax(value = "350", message = "最高速度 ${formatter.format('%1$.2f', validatedValue)} 不能超過 {value}")
    private double topSpeed;

    @DecimalMax(value = "100000", message = "價格不能高於 ${value}")
    private BigDecimal price;
}