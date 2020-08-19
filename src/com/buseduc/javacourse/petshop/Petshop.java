package com.buseduc.javacourse.petshop;

import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.AnimalInfo;
import com.buseduc.javacourse.petshop.bio.AnimalSex;
import com.buseduc.javacourse.petshop.bio.genes.Mammalia;
import com.buseduc.javacourse.petshop.bio.species.Cat;
import com.buseduc.javacourse.petshop.bio.species.Dog;
import com.buseduc.javacourse.petshop.ui.CustomerForm;

import java.util.*;

public class Petshop {
    private List<Animal> shopAnimals;
    private Map<String, Customer> shopCustomers;
    private Settings settings;
    private String name;
    private static Petshop petshop;
    public static void main(String[] args) {

        Settings settings = Settings.getInstance();
        Petshop shop = Petshop.getInstance(settings);
        shop.createAnimals();
        System.out.println(shop.shopAnimals);
        CustomerForm form = new CustomerForm(shop);
        Customer customer = form.showFormAndGetCustomer();
        shop.shopCustomers.put(customer.getName(), customer);
        System.out.println(customer.getAvailableMoney());
        Customer customer1 = new Customer("Petya", 150., Currency.RUB);
        shop.shopCustomers.put("Petya", customer1);
        System.out.println(shop.shopCustomers);

        System.out.println(customer.getAvailableMoney());

    }

    private void createAnimals() {
        List<String> animalNicks = this.getSettings().getNicks();
        List<Animal> result = new ArrayList<>();
        for(int i = 0; i < animalNicks.size(); i++) {
            String nick = animalNicks.get(i);
            Double price = this.getSettings().getPrices().get(i);
            AnimalInfo species = this.getSettings().getSpecies().get(i);
            String sexStr = this.getSettings().getSexes().get(i);
            AnimalSex sex = AnimalSex.getByCode(sexStr);
            Animal animal = new Animal(nick, price, Currency.EUR, species, sex);
            result.add(animal);




        }
        this.setShopAnimals(result);

    }


    private Petshop(Settings settings) {
        this.settings = settings;
        this.name = settings.getShopName();
        this.shopCustomers = new HashMap<>();

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
