package com.example.ecommerce.Service;


import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Model.MerchantStock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
            if (!(productService.products.get(i).getId() == merchantStock.getProductId())){
                return  1;
            }
        }

        for (int j = 1; j < merchantService.merchants.size(); j++) {
            if (!(merchantService.merchants.get(j).getId() == merchantStock.getMerchantId())){
                return  2;
            }

        }
        merchantStocks.add(merchantStock);
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
    public int checkProductId(int ProductId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getProductId() == ProductId) {
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

    public void reduceStock(int MerchantID){
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getMerchantId() == MerchantID) {
                merchantStock.setStock(merchantStock.getStock() - 1);
            }
        }
    }


    public ArrayList<MerchantStock> getAllLowStock(int merchantID)
    {
        ArrayList<MerchantStock> LowStock = new ArrayList<>();
        for (MerchantStock merchantStock : merchantStocks) {
            if (merchantStock.getMerchantId() == merchantID && merchantStock.getStock() <=10) {
                LowStock.add(merchantStock);
            }
        }
        return LowStock;
    }



}
