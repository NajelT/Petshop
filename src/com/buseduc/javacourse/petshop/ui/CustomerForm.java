package com.buseduc.javacourse.petshop.ui;

import com.buseduc.javacourse.petshop.Currency;
import com.buseduc.javacourse.petshop.Customer;
import com.buseduc.javacourse.petshop.Petshop;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Scanner;

public class CustomerForm {
    private Scanner input;
    private Petshop shop;

    public CustomerForm(Petshop shop) {
        this.shop = shop;
        input = new Scanner(System.in);
    }

    public Customer showFormAndGetCustomer() {
        String name = getCustomerName();
        Double amount = getAmount();
        return new Customer(name, amount, Currency.EUR);
    }

    String getCustomerName() {
        System.out.println("Enter your name:");
        String ret = input.nextLine();
        return ret;
    }

    Double getAmount() {
        System.out.println("Enter your budget:");
        String ret = input.nextLine();
        return Double.parseDouble(ret);
    }

}
