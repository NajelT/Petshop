package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.bio.genes.Fish;

public class Guppi extends Fish {
    public static String name = "Guppi";

    public Guppi() {
        super.setCategory(BioCategory.SPECIES);
    }
    @Override
    public String getName() {
        return name;
    }

}
