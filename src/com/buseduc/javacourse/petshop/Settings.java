package com.buseduc.javacourse.petshop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    public Properties readProperties() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("conf/petshop.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Не смогли прочитать настройки");
        }
        return properties;

    }
}
