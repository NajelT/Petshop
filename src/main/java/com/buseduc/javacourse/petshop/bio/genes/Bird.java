package com.buseduc.javacourse.petshop.bio.genes;

import com.buseduc.javacourse.petshop.bio.AnimalInfo;

public class Bird extends AnimalInfo {
    public static String name = "Bird";
    public Bird() {
        super.setCategory(BioCategory.GENUS);
    }

}
