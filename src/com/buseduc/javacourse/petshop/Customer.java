package com.buseduc.javacourse.petshop;

public class Customer {
    private String name;
    private Budget budget;
    private Integer age;

    public Customer(String name, Double amount, Currency currency, Integer age) {
        this.name = name;
        this.budget = new Budget(amount, currency);
        this.age = age;
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

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                "age='" + age + '\'' +
                ", budget=" + budget +
                '}';
    }
}
