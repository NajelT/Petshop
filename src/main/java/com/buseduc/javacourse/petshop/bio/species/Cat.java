package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.animalproperties.Allergable;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;
import com.buseduc.javacourse.petshop.animalproperties.Noise;
import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.genes.Mammalia;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cat extends Mammalia implements Noisy, Allergable {
    public static String name = "Cat";

    public Cat() {
        super.setCategory(BioCategory.SPECIES);
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public Noise makeNoise() {
        return new Noise("Meow", 0.1, true, 50.);
    }

    @Override
    public Set<Allergy> produceAllergy() {
        return new HashSet<>(Arrays.asList(Allergy.WOOL));
    }
}
