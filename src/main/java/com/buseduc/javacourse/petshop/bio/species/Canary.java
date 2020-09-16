package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.animalproperties.Noise;
import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.genes.Bird;

public class Canary extends Bird implements Noisy {
    public static String name = "Canary";

    public Canary() {
        super.setCategory(BioCategory.SPECIES);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Noise makeNoise() {
        return new Noise("Fewww", 4., true, 100.);
    }
}
