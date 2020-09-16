package com.buseduc.javacourse.petshop;

import java.time.LocalDateTime;

public class ChangeItem {
    Animal animal;
    Double currentShopBalance;
    LocalDateTime time;
    String itemType;

    public ChangeItem(Animal animal, Double currentShopBalance) {
        this.animal = animal;
        this.currentShopBalance = currentShopBalance;
        this.time = LocalDateTime.now();
    }

    public Animal getAnimal() {
        return animal;
    }

    public Double getCurrentShopBalance() {
        return currentShopBalance;
    }

    @Override
    public String toString() {
        return this.itemType +
                ": " + animal.getNick() + "(" + animal.getSpecies().getName() + ", " +
                animal.getPrice().getAmount() + ")\t\t" +
                " cur. balance:" + currentShopBalance +
                "\t\ttime:" + time;
    }
}
