package com.buseduc.javacourse.petshop;

import com.buseduc.javacourse.petshop.bio.AnimalInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    public List<AnimalInfo> getSpecies() {
        String specStr = getProperties().getProperty("shop.pets.species");
        List<String> speciesList = Arrays.asList(specStr.split(","));
        List<AnimalInfo> result = new ArrayList<>();
        for(String next : speciesList) {
            try {
                Integer key = Integer.parseInt(next);
                AnimalInfo nextSpecies = AnimalInfo.SPECIES_MAP.get(key);
                result.add(nextSpecies);
            } catch (NumberFormatException nfe) {
                continue;
            }
        }
        return result;
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
