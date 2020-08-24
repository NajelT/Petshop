package com.buseduc.javacourse.petshop.ui;

import com.buseduc.javacourse.petshop.Animal;
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
            printAnimalsList(customer.getAvailableMoney());
            askCustomerToChooseAnimal(customer);




        }
    }


    private void askCustomerToChooseAnimal(Customer customer) {
        int animalId;
        System.out.println("Please choose animal (type animal id):");
        while (true) {
            try {
                animalId = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect id. Please enter it again:");
            }
        }
        Animal toBuy = shop.findAnimalById(animalId);
        if (customer.payForAnimal(toBuy)) {
            System.out.println("Deal succeeded");
            toBuy.setOwner(customer);
        } else {
            System.out.println("Deal failed");
        }
        System.out.println(customer);
        printAnimalsList(customer.getAvailableMoney());
        System.out.println(shop.getShopAnimals());


    }

    public void printAnimalsList(Double availableMoney) {
        System.out.println("Here are the animals you can buy:");
        System.out.println("__________________________________");
        shop.getShopAnimals().stream()
                .filter(animal -> animal.getOwner() == null && animal.getPrice().getAmount() <= availableMoney)
                .forEach(animal -> {
                    System.out.println(
                        animal.getId() +  " - \t" +
                        animal.getSpecies().getName() + " - \t" +
                        animal.getNick() +
                        " \t (" + animal.getPrice().toString() + ")");
        });
        System.out.println("__________________________________");

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
