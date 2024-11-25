package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Service.MerchantService;
import com.example.ecommerce.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/merchant")
public class MerchantController {

    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getMerchants() {
        if (merchantService.getMerchants().isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("List of Merchant is Empty"));
        }
        return ResponseEntity.ok(merchantService.getMerchants());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
       if (errors.hasErrors()) {
           return ResponseEntity.status(400).body(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage()));
       }
            merchantService.addMerchants(merchant);
           return ResponseEntity.status(200).body(new ApiResponse("Merchant Added"));
       }

       @PutMapping("/update/{index}")
       public ResponseEntity<ApiResponse> updateMerchant(@PathVariable int index,@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage()));
        }
            if (merchantService.updateMerchant(index, merchant)) {
                return ResponseEntity.status(200).body(new ApiResponse("Merchant Updated"));
            }
            return ResponseEntity.status(404).body(new ApiResponse("Merchant Not Found"));
       }

       @DeleteMapping("/delete/{index}")
      public ResponseEntity<ApiResponse> deleteMerchant(@PathVariable int index ) {

       if(merchantService.deleteMerchant(index)){
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Deleted"));
      }
      return ResponseEntity.status(404).body(new ApiResponse("Merchant Not Found"));
      }

        @PutMapping("/addMoreStock/{indexMerchant}/{indexProductId}")
      public ResponseEntity<ApiResponse> addMoreStock(@PathVariable int indexMerchant, @PathVariable int indexProductId, MerchantStock merchantStock, Errors errors) {

        if (merchantService.checkMerchantId(indexMerchant) == 1)
        {
            if (merchantStockService.checkProductId(indexProductId) == 1)
            {
                merchantStockService.updateMerchantStocks(indexProductId, merchantStock);
                return ResponseEntity.status(200).body(new ApiResponse("Stock added"));
            }
            return ResponseEntity.status(400).body(new ApiResponse("product Not Found"));

        }
        return ResponseEntity.status(404).body(new ApiResponse("Merchant Not Found"));
      }

 }

