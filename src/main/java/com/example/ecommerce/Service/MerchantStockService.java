package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Model.MerchantStock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MerchantStockService {

     private final ProductService productService;
     private final MerchantService merchantService;


    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public int addMerchantStocks(MerchantStock merchantStock) {

        for (int i = 0; i < productService.products.size(); i++) {

            if (productService.products.get(i).getId() == merchantStock.getProductId()){

                for (int j = i + 1; j < merchantService.merchants.size(); j++) {
                    if (merchantService.merchants.get(j).getId() == merchantStock.getMerchantId()){
                        merchantStocks.add(merchantStock);
                        return  1;
                    }

                }
                return  2;
            }
        }
          return 0;
    }

    public boolean updateMerchantStocks(int index ,MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == index) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStocks(int index) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == index) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }
    public int checkProductId(int index) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getProductId() == index){
                return 1;
            }
        }
        return 0;
    }


    public int getStockQuantity(int merchantId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getMerchantId() == merchantId && merchantStock.getStock() >= 1) {
                return merchantStock.getStock();
            }
        }
        return 0;
    }

    public void reduceStock(int indexMerchant, int reduce){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == indexMerchant){
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() - reduce);
            }
        }
    }



}
