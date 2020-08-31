package com.buseduc.javacourse.petshop.users;

import com.buseduc.javacourse.petshop.Animal;
import com.buseduc.javacourse.petshop.Currency;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;

public class Customer extends User{
    private Budget budget;
    private Integer age;
    private Allergy allergy;

    public Customer(String name, Double amount, Currency currency, Integer age, Allergy allergy) {
        super(name);
        this.budget = new Budget(amount, currency);
        this.age = age;
        this.allergy = allergy;
    }

    private class Budget {
        private Double fullMoney;
        private Currency currency;

        public Budget(Double fullMoney, Currency currency) {
            this.fullMoney = fullMoney;
            this.currency = currency;
        }

        @Override
        public String toString() {
            return fullMoney + " " + currency;
        }
    }


    public Double getAvailableMoney() {
        return this.budget.fullMoney;
    }
    public boolean payForAnimal(Animal animal) {
        if (this.budget.fullMoney >= animal.getPrice().getAmount()) {
            this.budget.fullMoney -= animal.getPrice().getAmount();
            return true;
        }
        return false;
    }

    public Allergy getAllergy() {
        return allergy;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + getName() + '\'' +
                "age='" + age + '\'' +
                ", budget=" + budget +
                '}';
    }
}
