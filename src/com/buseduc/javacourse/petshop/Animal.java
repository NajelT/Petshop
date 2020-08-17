package com.buseduc.javacourse.petshop;

import com.buseduc.javacourse.petshop.bio.AnimalInfo;
import com.buseduc.javacourse.petshop.bio.AnimalSex;

public class Animal {
    private String nick;
    private double price;
    private AnimalInfo species;
    private AnimalSex sex;

    public Animal(String nick, double price, AnimalInfo species, AnimalSex sex) {
        this.nick = nick;
        this.price = price;
        this.species = species;
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "Animal{" +
                "nick='" + nick + '\'' +
                ", price=" + price +
                ", species=" + species +
                ", sex=" + sex +
                '}';
    }
}
