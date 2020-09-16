package com.buseduc.javacourse.petshop.bio.species;

import com.buseduc.javacourse.petshop.animalproperties.Noise;
import com.buseduc.javacourse.petshop.animalproperties.Noisy;
import com.buseduc.javacourse.petshop.bio.genes.Amphibia;

public class Frog extends Amphibia  implements Noisy {
    public static String name = "Frog";

    public Frog() {
        super.setCategory(BioCategory.SPECIES);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Noise makeNoise() {
        return new Noise("Kwa", .1, false, 55.);
    }
}
