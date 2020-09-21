package com.buseduc.javacourse.petshop.users;

import com.buseduc.javacourse.petshop.Animal;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "budget_amount")
    private Double budget;
    @Column(name = "age")
    private Integer age;
    @Column(name = "allergy")
    @Enumerated(EnumType.STRING)
    private Allergy allergy;

    public Customer() {}
    public Customer(String name, Double budget, Integer age, Allergy allergy) {
        this.name = name;
        this.budget = budget;
        this.age = age;
        this.allergy = allergy;
    }

/*
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
*/


    public Double getAvailableMoney() {
        return this.budget;
    }
    public boolean payForAnimal(Animal animal) {
        if (this.budget >= animal.getPrice().getAmount()) {
            this.budget -= animal.getPrice().getAmount();
            return true;
        }
        return false;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAllergy(Allergy allergy) {
        this.allergy = allergy;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
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
