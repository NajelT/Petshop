package com.buseduc.javacourse.petshop.animalproperties;

public class Noise {
    String voice;
    Double maxVolume;
    Boolean isMelodic;
    Double averageTone;

    public Noise(String voice, Double maxVolume, Boolean isMelodic, Double averageTone) {
        this.voice = voice;
        this.maxVolume = maxVolume;
        this.isMelodic = isMelodic;
        this.averageTone = averageTone;
    }


    @Override
    public String toString() {
        return "Noise{" +
                "voice=" + voice +
                ", maxVolume=" + maxVolume +
                ", isMelodic=" + isMelodic +
                ", averageTone=" + averageTone +
                '}';
    }
}
