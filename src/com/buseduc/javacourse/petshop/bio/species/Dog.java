package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.animalproperties.Noise;
import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.genes.Mammalia;

public class Dog extends Mammalia implements Noisy {
    public static String name = "Dog";

    public Dog() {
        super.setCategory(BioCategory.SPECIES);
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public Noise makeNoise() {
        Noise dogHau = new Noise("Hau", 5., false, 20.);
        return dogHau;
    }
}
