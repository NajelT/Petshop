package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.animalproperties.Allergable;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;
import com.buseduc.javacourse.petshop.bio.genes.Mammalia;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Hamster extends Mammalia implements Allergable {
    public static String name = "Hamster";

    public Hamster() {
        super.setCategory(BioCategory.SPECIES);
    }

    @Override
    public String getName() {
        return name;
    }



    @Override
    public Set<Allergy> produceAllergy() {
        return new HashSet<>(Arrays.asList(Allergy.DUST, Allergy.WOOL));
    }
}
