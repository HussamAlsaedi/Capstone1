package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor

public class UserService {

    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    ArrayList<User> users = new ArrayList<>();

    public ArrayList<User> getAllUsers(){
        return users;
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    public  boolean updateUser(int index, User user){
        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getId() == index){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int index){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == index){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public int checkUserid(int id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id){
                return 1;
            }
        }
        return 0;
    }

    public int get(int id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id){
                return 1;
            }
        }
        return 0;
    }

    public void deductedBalance(int indexUser, double deducted){
        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getId() == indexUser){
                users.get(i).setBalance(users.get(i).getBalance() - deducted);
            }
        }
    }

    public int userBuy(int indexUser, int indexMerchant, int indexProduct){

        if (merchantStockService.getStockQuantity(indexMerchant) >= 1) {
            merchantStockService.reduceStock(indexMerchant, 1);

            if (productService.getProductPrice(indexProduct) <= users.get(indexUser).getBalance()) {
                deductedBalance(indexUser, productService.getProductPrice(indexProduct));
                return 1;
            } else { return 3;}
        }
        return 2;
    }

    public boolean updateUserBalance(int userId, double newBalance) {
        for (User user : users) {
            if (user.getId() == userId) {
                user.setBalance(user.getBalance() + newBalance);
                return true;
            }
        }
        return false;
    }

    public Double checkUserBalance(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user.getBalance();
            }
        }
        return null;
    }




}
