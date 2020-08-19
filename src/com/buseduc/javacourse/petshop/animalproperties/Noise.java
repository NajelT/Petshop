package com.buseduc.javacourse.petshop.animalproperties;

public class Noise {
    String onomatopeia;
    Double maxVolume;
    Boolean isMelodic;
    Double averageTone;

    public Noise(String onomatopeia, Double maxVolume, Boolean isMelodic, Double averageTone) {
        this.onomatopeia = onomatopeia;
        this.maxVolume = maxVolume;
        this.isMelodic = isMelodic;
        this.averageTone = averageTone;
    }


    @Override
    public String toString() {
        return "Noise{" +
                "onomatopeia=" + onomatopeia +
                ", maxVolume=" + maxVolume +
                ", isMelodic=" + isMelodic +
                ", averageTone=" + averageTone +
                '}';
    }
}
