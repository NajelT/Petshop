package com.buseduc.javacourse.petshop;

public class SaleItem extends ChangeItem {

    public SaleItem(Animal animal, Double currentShopBalance) {
        super(animal, currentShopBalance);
        this.itemType = "SALE";
    }
}
