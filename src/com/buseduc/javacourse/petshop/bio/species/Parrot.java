package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.bio.genes.Bird;

public class Parrot extends Bird {
    public static String name = "Parrot";

    public Parrot() {
        super.setCategory(BioCategory.SPECIES);
    }
    @Override
    public String getName() {
        return name;
    }

}
