package com.buseduc.javacourse.petshop;

import com.buseduc.javacourse.petshop.users.AdminService;
import com.buseduc.javacourse.petshop.users.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SalesHistory {
    private static SalesHistory history;


    Double balance;
    List<SaleItem> saleItems = new ArrayList<>();

    private SalesHistory() {}

    public class SaleItem {
        Animal soldAnimal;
        LocalDateTime time;
        Customer owner;
        Double currentShopBalance;

        public SaleItem(Animal soldAnimal, Customer owner) {
            this.soldAnimal = soldAnimal;
            this.owner = owner;
            this.time = LocalDateTime.now();
        }
    }

    public static SalesHistory getInstance() {
        if(history == null) {
            history = new SalesHistory();
        }
        return history;
    }

    public void update(Animal sold, Customer owner) {
        SaleItem saleItem = new SaleItem(sold, owner);
        saleItems.add(saleItem);

    }

}
