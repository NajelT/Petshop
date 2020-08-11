package com.buseduc.javacourse.petshop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Petshop {
    public static void main(String[] args) {
        Settings shopSettings = new Settings();
        Properties properties = shopSettings.readProperties();
        String shopName = properties.getProperty("shop.name");
        System.out.println("Pet Shop: " + shopName );

    }
}
