package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Merchant;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MerchantService {

   ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public void addMerchants(Merchant merchant) {
       merchants.add(merchant);
    }

    public boolean updateMerchant(int index , Merchant merchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == index){
                merchants.set(i,merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(int index) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == index){
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    public int checkMerchantId(int index) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId() == index){
                return 1;
            }
        }
        return 0;
    }

}
