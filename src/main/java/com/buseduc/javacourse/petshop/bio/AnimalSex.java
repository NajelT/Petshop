package com.buseduc.javacourse.petshop.bio;

public enum AnimalSex {
    MALE("m"), FEMALE("f");

    String code;

    AnimalSex(String code) {
        this.code = code;
    }

    public static AnimalSex getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (AnimalSex sex: AnimalSex.values()) {
            if(code.equals(sex.code)) {
                return sex;
            }
        }
        return null;

    }
}
