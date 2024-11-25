package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CategoryService {

      ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getAllCategories(){
        return categories;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public boolean updateCategory(int index, Category category){
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId()== index){
                categories.set(i,category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(int index){
        for (int i = 0; i <categories.size() ; i++) {
            if (categories.get(i).getId() == index){
                categories.remove(i);
                return true;
            }
        }
        return false;
    }

    public int checkCategoryId(int id){
        for (int i = 0; i <categories.size() ; i++) {
            if (categories.get(i).getId() == id){
                return 1;
            }
        }
        return 0;
    }
}
