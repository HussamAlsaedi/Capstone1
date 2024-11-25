package com.example.ecommerce.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    @NotNull(message = "id must be not empty.")
    private int id;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 3, message = "name must not be empty, have to be more than 3 length long).")
    private String name;
}
