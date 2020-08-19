package com.buseduc.javacourse.petshop;

public class Customer {
    private String name;
    private Budget budget;

    public Customer(String name, Double amount, Currency currency) {
        this.name = name;
        this.budget = new Budget(amount, currency);
    }

    private class Budget {
        Double fullMoney;
        Currency currency;

        public Budget(Double fullMoney, Currency currency) {
            this.fullMoney = fullMoney;
            this.currency = currency;
        }
    }
    public Double getAvailableMoney() {
        return this.budget.fullMoney;
    }
}
