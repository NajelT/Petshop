package com.buseduc.javacourse.petshop;

public class ReceiveItem extends ChangeItem {

    public ReceiveItem(Animal animal, Double currentShopBalance) {
        super(animal, currentShopBalance);
        this.itemType = "RECEIVE";
    }
}
