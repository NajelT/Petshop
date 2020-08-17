package com.buseduc.javacourse.petshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Petshop {
    private List<Animal> shopAnimals;
    private Settings settings;
    private String name;
    private static Petshop petshop;
    public static void main(String[] args) {

        Settings settings = Settings.getInstance();
        Petshop shop = Petshop.getInstance(settings);
        shop.createAnimals();
        System.out.println(shop.shopAnimals);
        System.out.println("Pet nicks: " + shop.getSettings().getNicks());
        System.out.println("Pet specs: " + shop.getSettings().getSpecies());
        System.out.println("Pet sexs: " + shop.getSettings().getSexes());
        System.out.println("Pet prices: " + shop.getSettings().getPrices());
    }

    private void createAnimals() {
        List<String> animalNicks = this.getSettings().getNicks();
        List<Animal> result = new ArrayList<>();
        for(int i = 0; i < animalNicks.size(); i++) {
            String nick = animalNicks.get(i);
            Double price = this.getSettings().getPrices().get(i);
            Animal animal = new Animal(nick, price, null, null);
            result.add(animal);


        }
        this.setShopAnimals(result);

    }


    private Petshop(Settings settings) {
        this.settings = settings;
        this.name = settings.getShopName();

    }

    public List<Animal> getShopAnimals() {
        return shopAnimals;
    }

    public void setShopAnimals(List<Animal> shopAnimals) {
        this.shopAnimals = shopAnimals;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public static Petshop getInstance(Settings settings) {
        if(petshop == null) {
            petshop = new Petshop(settings);
        }
        return petshop;
    }
}
