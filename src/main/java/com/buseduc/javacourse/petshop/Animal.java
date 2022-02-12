package com.buseduc.javacourse.petshop;

import com.buseduc.javacourse.petshop.animalproperties.Allergable;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;
import com.buseduc.javacourse.petshop.animalproperties.Noise;
import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.AnimalInfo;
import com.buseduc.javacourse.petshop.bio.AnimalSex;
import com.buseduc.javacourse.petshop.users.Customer;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Currency;
import java.util.Set;

public class Animal {
    private String nick;
    private Price price;
    private AnimalInfo species;
    private AnimalSex sex;
    private int id;
    private Customer owner;
    private LocalDateTime inShopFrom;
    private static int counter = 0;

    public Animal(String nick, double price, Currency currency, AnimalInfo species, AnimalSex sex) {
        this.nick = nick;
        this.price = new Price(price, currency);
        this.species = species;
        this.sex = sex;
        this.id = counter++;
        this.inShopFrom = LocalDateTime.now();

    }


    public class Price {
        private double amount;
        private Currency currency;

        public Price(double amount, Currency currency) {
            this.amount = amount;
            this.currency = currency;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        @Override
        public String toString() {
            return amount + " " + currency;
        }
    }

    public String getNick() {
        return nick;
    }


    public void setNick(String nick) {
        this.nick = nick;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public AnimalInfo getSpecies() {
        return species;
    }

    public void setSpecies(AnimalInfo species) {
        this.species = species;
    }

    public AnimalSex getSex() {
        return sex;
    }

    public void setSex(AnimalSex sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCounter() {
        return counter;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public static void setCounter(int counter) {
        Animal.counter = counter;
    }

    public Set<Allergy> getPossibleAllergies() {
        if (this.species instanceof Allergable) {
            return ((Allergable) this.species).produceAllergy();
        }
        return Collections.emptySet();
    }
    public Noise getPossibleNoise() {
        if (this.species instanceof Noisy) {
            return ((Noisy) this.species).makeNoise();
        }
        return null;
    }

    @Override
    public String toString() {
        String ownerName = owner == null ? "-" : owner.getName();
        Noise noise = getPossibleNoise();
        String noiseStr = noise == null ? "" : noise.toString();
        return id + ". \t" +
                 nick + "\t\t(" + species.getName() + ", " + sex + ")\t" +
                price + "\t\t owner:" + ownerName +
                "\n\t\tallergies: " + this.getPossibleAllergies().toString() + " \t\t" +
                 "noise: " + noiseStr + " \t\t" +
                 "\t\tin shop from: " + inShopFrom;
    }
}
