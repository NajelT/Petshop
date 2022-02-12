package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.animalproperties.Allergable;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;
import com.buseduc.javacourse.petshop.animalproperties.Noise;
import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.genes.Bird;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parrot extends Bird implements Noisy, Allergable {
    public static String name = "Parrot";

    public Parrot() {
        super.setCategory(BioCategory.SPECIES);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Noise makeNoise() {
        return new Noise("Popka durak", 2., false, 60.);
    }

    @Override
    public Set<Allergy> produceAllergy() {
        return new HashSet<>(Arrays.asList(Allergy.SMELL));
    }
}
