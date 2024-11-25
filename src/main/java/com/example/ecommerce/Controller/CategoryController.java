package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity getAllCategories(){

        if (categoryService.getAllCategories().isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("List of Category is empty"));
        }
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody @Valid Category category, Errors errors){

        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse( errors.getAllErrors().get(0).getDefaultMessage()));
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("Category is added successfully"));
    }


    @PutMapping("/update/{index}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable int index, Category category,Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getAllErrors().get(0).getDefaultMessage()));
        }

        if(categoryService.updateCategory(index,category)){
            return ResponseEntity.status(200).body(new ApiResponse("Category is updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Category not found"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int index){
        if (categoryService.deleteCategory(index)){
            return ResponseEntity.status(200).body(new ApiResponse("Category delete successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Category not found"));
    }
}
