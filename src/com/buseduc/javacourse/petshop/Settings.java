package com.buseduc.javacourse.petshop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Settings {
    private static final Settings settings = new Settings();
    private Properties properties;
    private Settings() { }

    private Properties readProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("conf/petshop.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Не смогли прочитать настройки");
        }
        return properties;
    }
    public String getShopName() {
        return getProperties().getProperty("shop.name");

    }


    public List<String> getNicks() {
        String nicksStr = getProperties().getProperty("shop.pets.nicks");
        return Arrays.asList(nicksStr.split(","));
    }

    public List<String> getSpecies() {
        String specStr = getProperties().getProperty("shop.pets.species");
        return Arrays.asList(specStr.split(","));
    }

    public List<String> getSexes() {
        String sexStr = getProperties().getProperty("shop.pets.sex");
        return Arrays.asList(sexStr.split(","));
    }

    public List<String> getPrices() {
        String priceStr = getProperties().getProperty("shop.pets.prices");
        return Arrays.asList(priceStr.split(","));
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }

    public static Settings getInstance() {
        return settings;
    }
}
