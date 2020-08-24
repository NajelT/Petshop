package com.buseduc.javacourse.petshop.ui;

import com.buseduc.javacourse.petshop.Currency;
import com.buseduc.javacourse.petshop.Customer;
import com.buseduc.javacourse.petshop.Petshop;

import java.util.Scanner;

public class CustomerService {
    private Scanner input;
    private Petshop shop;

    public CustomerService(Petshop shop) {
        this.shop = shop;
        input = new Scanner(System.in);
    }

    public void start() {
        while(true) {
            String name = getCustomerName();
            Customer customer = loginOrRegister(name);
            shop.getShopCustomers().put(customer.getName(), customer);
            System.out.println(shop.getShopCustomers());

        }
    }
    public Customer loginOrRegister(String name) {
        if (shop.getShopCustomers().containsKey(name)) {
            System.out.println("Hello again, " + name);
            return shop.getShopCustomers().get(name);
        }
        Double amount = getAmount();
        Integer age = getCustomerAge();
        System.out.println("Welcome, " + name);
        return new Customer(name, amount, Currency.EUR, age);
    }

    String getCustomerName() {
        System.out.println("Enter your name:");
        String ret;
        while(true) {
            ret = input.nextLine();
            if (ret != null && !"".equals(ret)) {
                break;
            }
            System.out.println("Incorrect name. Please enter name again:");

        }
        return ret;
    }

    Double getAmount() {
        System.out.println("Enter your budget:");
        Double budget = null;
        while (true) {
            try {
                budget = Double.parseDouble(input.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect budget. Please enter it again:");
            }
        }
        return budget;
    }

    Integer getCustomerAge() {
        System.out.println("Enter your age:");
        Integer age = null;
        while (true) {
            try {
                age = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect age. Please enter it again:");
            }
        }
        return age;
    }

}
