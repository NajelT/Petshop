package com.buseduc.javacourse.petshop;

import java.util.Properties;

public class Petshop {
    public static void main(String[] args) {
        Settings shopSettings = Settings.getInstance();
        String shopName = shopSettings.getShopName();
        System.out.println("Pet Shop: " + shopName );
        System.out.println("Pet nicks: " + shopSettings.getNicks());
        System.out.println("Pet specs: " + shopSettings.getSpecies());
        System.out.println("Pet sexs: " + shopSettings.getSexes());

    }
}
