package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.bio.genes.Reptilia;

public class Crocodile extends Reptilia {
    public static String name = "Crocodile";

    public Crocodile() {
        super.setCategory(BioCategory.SPECIES);
    }
    @Override
    public String getName() {
        return name;
    }

}
