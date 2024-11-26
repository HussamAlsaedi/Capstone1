package com.example.ecommerce.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotNull(message = "Id must be not empty.")
    private int id;

    @NotNull(message = "productId must be not empty")
    private int productId;

    @NotNull(message = "merchantId must be  not empty")
    private int merchantId;

    @NotNull(message = "Stock must be  not empty")
    @Min(value = 10 ,message = "stock must not be empty, have to be more than 10 at start).")
    private int stock;

}
