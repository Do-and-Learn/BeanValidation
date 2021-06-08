package demo;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class RentalStation {
    public RentalStation(@NotNull String name) {
    }

    @Valid
    public void foo(@NotNull String foo) {

    }
}

