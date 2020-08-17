package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.bio.genes.Mammalia;

public class Cat extends Mammalia {
    public static String name = "Cat";

    public Cat() {
        super.setCategory(BioCategory.SPECIES);
    }

    @Override
    public String getName() {
        return name;
    }


}
