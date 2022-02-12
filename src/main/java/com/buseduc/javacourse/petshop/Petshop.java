package com.buseduc.javacourse.petshop;

import com.buseduc.javacourse.petshop.*;
import com.buseduc.javacourse.petshop.bio.AnimalInfo;
import com.buseduc.javacourse.petshop.bio.AnimalSex;
import com.buseduc.javacourse.petshop.users.Customer;
import com.buseduc.javacourse.petshop.users.CustomerService;

import java.util.Currency;
import java.util.*;

public class Petshop {
    private List<com.buseduc.javacourse.petshop.Animal> shopAnimals;

    public Map<String, Customer> getShopCustomers() {
        return shopCustomers;
    }

    public void setShopCustomers(Map<String, Customer> shopCustomers) {
        this.shopCustomers = shopCustomers;
    }

    private Map<String, Customer> shopCustomers;
    private com.buseduc.javacourse.petshop.Settings settings;
    private com.buseduc.javacourse.petshop.ChangesHistory changesHistory;
    private String name;
    private static Petshop petshop;
    private double balance;
    public static void main(String[] args) {

        Petshop shop = Petshop.getInstance();
        shop.createAnimals();
//        System.out.println(shop.shopAnimals);
        CustomerService service = new CustomerService(shop);
        service.start();

    }

    public com.buseduc.javacourse.petshop.Animal findAnimalById(int animalId) {
        return getShopAnimals()
                .stream()
                .filter(animal -> animal.getId() == animalId)
                .findAny()
                .orElse(null);
    }

    public void createAnimals() {
        List<String> animalNicks = this.getSettings().getNicks();
        List<com.buseduc.javacourse.petshop.Animal> result = new ArrayList<>();
        for(int i = 0; i < animalNicks.size(); i++) {
            String nick = animalNicks.get(i);
            Double price = this.getSettings().getPrices().get(i);
            AnimalInfo species = this.getSettings().getSpecies().get(i);
            String sexStr = this.getSettings().getSexes().get(i);
            AnimalSex sex = AnimalSex.getByCode(sexStr);
            com.buseduc.javacourse.petshop.Animal animal = createAnimal(nick, price, species, sex);
            result.add(animal);




        }
        this.setShopAnimals(result);

    }

    public Animal createAnimal(String nick, double price, AnimalInfo species, AnimalSex sex) {
        this.balance += price;
        return new Animal(nick, price, null ,species, sex);
    }


    private Petshop(Settings settings) {
        this.settings = settings;
        this.name = settings.getShopName();
        this.shopCustomers = new HashMap<>();
        this.changesHistory = ChangesHistory.getInstance();

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ChangesHistory getChangesHistory() {
        return changesHistory;
    }

    public String getName() {
        return name;
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

    public static Petshop getInstance() {
        if(petshop == null) {
            petshop = new Petshop(Settings.getInstance());
        }
        return petshop;
    }
}
