package com.buseduc.javacourse.petshop.bio;

import com.buseduc.javacourse.petshop.bio.species.*;

import java.util.HashMap;
import java.util.Map;

public abstract class AnimalInfo {
    public static String name;

    private BioCategory category;
    public static final Map<Integer, AnimalInfo> SPECIES_MAP = new HashMap<>();
    static {
        SPECIES_MAP.put(0, new Cat());
        SPECIES_MAP.put(1, new Crocodile());
        SPECIES_MAP.put(2, new Frog());
        SPECIES_MAP.put(3, new Guppi());
        SPECIES_MAP.put(4, new Parrot());

    }

    public enum BioCategory {
        GENUS, SPECIES
    }

    public BioCategory getCategory() {
        return category;
    }

    public void setCategory(BioCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AnimalInfo{ " +
                category + ": " +
                getName() +

                "}";
    }
}
