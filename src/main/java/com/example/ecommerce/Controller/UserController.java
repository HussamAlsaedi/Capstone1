package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.MerchantService;
import com.example.ecommerce.Service.MerchantStockService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final MerchantService merchantService;


    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        if (userService.getAllUsers().isEmpty())
        {
            return ResponseEntity.status(400).body(new ApiResponse("List is empty"));
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getAllErrors().get(0).getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateUser(@PathVariable int index, @RequestBody @Valid User user,Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getAllErrors().get(0).getDefaultMessage());
        }

        if(userService.updateUser(index,user)){
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));

        }
        return ResponseEntity.status(403).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteUser(@PathVariable int index){
        if (userService.deleteUser(index))
        {
            return ResponseEntity.status(200).body(new ApiResponse("User delete successfully"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @GetMapping("buy/{indexUser}/{indexProduct}/{indexMerchant}")
    public ResponseEntity<ApiResponse> buyProduct(@PathVariable int indexUser, @PathVariable int indexProduct, @PathVariable int indexMerchant){



        if (userService.checkUserid(indexUser)==1){
            if (productService.checkProductId(indexProduct)==1){
                if (merchantService.checkMerchantId(indexMerchant)==1){

                    if (userService.userBuy(indexMerchant,indexUser, indexProduct)== 1){
                        userService.userBuy(indexUser,indexMerchant,indexProduct);
                        return ResponseEntity.status(200).body(new ApiResponse("User buy successfully"));
                    }

                    if (userService.userBuy(indexMerchant,indexUser, indexProduct)== 2){
                        return ResponseEntity.status(200).body(new ApiResponse("Out Of Stock"));
                    }


                }
                return ResponseEntity.status(404).body(new ApiResponse("Merchant not found"));
            }
            return ResponseEntity.status(404).body(new ApiResponse("Product not found"));
        }
        return ResponseEntity.status(403).body(new ApiResponse("User not found"));
    }

    @PutMapping("/updateUserBalance/{index}/{newBalance}")
    public ResponseEntity<ApiResponse> updateUserBalance(@PathVariable int index,@PathVariable double newBalance)
    {
       if (userService.updateUserBalance(index,newBalance)){
           return ResponseEntity.status(200).body(new ApiResponse("User's Balance updated successfully"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @GetMapping("get-balance/{index}")
    public ResponseEntity getUserBalance(@PathVariable int index) {
        Double balance = userService.checkUserBalance(index);
        if (balance != null) {
            return ResponseEntity.ok(balance);
        }
        return ResponseEntity.status(402).body("User not found");
    }
}
