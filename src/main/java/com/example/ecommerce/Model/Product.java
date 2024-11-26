package com.example.ecommerce.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Product {

    @NotNull(message = "Id must not be empty.")
    private int id;
    @NotEmpty(message = "Name must not be empty.")
    @Size(min = 4, message = "Name must be more than 3 characters long")
    private String name;

    @NotNull(message = "Price must not be empty, must be positive number")
    @Positive(message = "Number must be positive")
    private double price;

    @NotNull(message = "Category_id must be not empty")
    private int  categoryId;

}
