package com.buseduc.javacourse.petshop.bio.genes;

import com.buseduc.javacourse.petshop.bio.AnimalInfo;

public class Fish extends AnimalInfo {
    public static String name = "Fish";

    public Fish() {
        super.setCategory(BioCategory.SPECIES);
    }
}
