package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.animalproperties.Allergable;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;
import com.buseduc.javacourse.petshop.animalproperties.Noise;
import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.genes.Mammalia;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dog extends Mammalia implements Noisy, Allergable {
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
        return new Noise("Hau", 5., false, 20.);
    }

    @Override
    public Set<Allergy> produceAllergy() {
        return new HashSet<>(Arrays.asList(Allergy.WOOL, Allergy.SMELL));
    }
}
