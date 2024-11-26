package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductService {


     private final CategoryService categoryService;
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getAllProducts(){
        return products;
    }

    public int addProduct(Product product)
    {
        for (int i = 0; i < categoryService.categories.size(); i++) {

            if (categoryService.categories.get(i).getId() == product.getCategoryId()) {
                products.add(product);
                return 1;
            }
        }
        return 0;

    }

     public  boolean updateProduct(int index, Product product){
         for (int i = 0; i <products.size() ; i++) {
             if (products.get(i).getId() == index){
                 products.set(i,product);
                 return true;
             }
         }
         return false;
     }

     public boolean deleteProduct(int index){
         for (int i = 0; i < products.size(); i++) {
             if (products.get(i).getId() == index){
                 products.remove(i);
                 return true;
             }
         }
         return false;
     }


     public int checkProductId(int id){
         for (Product product : products) {
             if (product.getId() == id) {
                 return 1;
             }
         }
        return 0;
     }

     public double getProductPrice(int index){
         for (Product product : products) {
             if (product.getId() == index) {
                 return product.getPrice();
             }
         }
        return 0;
     }


    public ArrayList<Product> getProductById(int index) {

        ArrayList<Product> matchingProducts = new ArrayList<>();

        for(Product product : products) {
            if (product.getId() == index) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public ArrayList<Product> getProductByCategory(int index) {

        ArrayList<Product> matchingCategory = new ArrayList<>();

        for(Product product : products) {
            if (product.getCategoryId() == index) {
                matchingCategory.add(product);
            }
        }
        return matchingCategory;
    }

     public ArrayList<Integer> getProductCount(int index) {

        ArrayList<Integer> countProducts = new ArrayList<>();

        countProducts.add(index);

        return countProducts;
     }



    public double checkProductBalance(int index) {
        for (Product product : products) {
            if (product.getId() == index) {
                return product.getPrice();
            }
        }
        return 0;
    }

}
