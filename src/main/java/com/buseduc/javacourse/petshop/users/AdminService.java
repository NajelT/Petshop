package com.buseduc.javacourse.petshop.users;

import com.buseduc.javacourse.petshop.*;
import com.buseduc.javacourse.petshop.bio.AnimalInfo;
import com.buseduc.javacourse.petshop.bio.AnimalSex;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.buseduc.javacourse.petshop.bio.AnimalInfo.SPECIES_MAP;

public class AdminService {
    private static AdminService service;
    private Petshop shop;
    private Scanner input;

    private AdminService(Petshop shop) {
        this.shop = shop;
        input = new Scanner(System.in);

    }

    public static AdminService getInstance(Petshop shop) {
        if(service == null) {
            service = new AdminService(shop);
        }
        return service;
    }

    boolean handleAdmin(Admin admin) {
        while (true) {
            switch (getAdminCommand()) {
                case -1:
                    return false;
                case 1:
                    printListOfAnimals();
                    break;
                case 2:
                    addNewAnimal();
                    break;
                case 3:
                    printListOfSales();
                    break;
                case 4:
                    printListOfReceives();
                    break;
                case 5:
                    printListOfTransactions();
                    break;
                case 6:
                    printListOfCustomers();

            }
        }

    }

    private void addNewAnimal() {
        String nick = getAnimalNick();
        Double price = getPrice();
        AnimalInfo species = getSpecies();
        AnimalSex sex = getSex();
        Animal newAnimal = shop.createAnimal(nick, price, species, sex);
        List<Animal> shopAnimals = shop.getShopAnimals();
        shopAnimals.add(newAnimal);
        shop.getChangesHistory().update(newAnimal, shop.getBalance(), false);
        shop.setShopAnimals(shopAnimals);


    }

    AnimalInfo getSpecies() {
        System.out.println("Choose AnimalInfo (press Enter if you don't have any):");
        AnimalInfo ret = null;
        for(Map.Entry<Integer, AnimalInfo> species: SPECIES_MAP.entrySet()) {
            System.out.println(species.getKey() + " - " + species.getValue().getName());
        }
        while(true) {
            String next = input.nextLine();
            if ("".equals(next)) {
                break;
            }
            try {
                Integer index = Integer.parseInt(next);
                ret = SPECIES_MAP.get(index);
                return ret;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe ) {
                System.out.println("Incorrect index of allergy. Please try again:");
            }
        }
        return ret;
    }


    Double getPrice() {
        System.out.println("Enter animal price:");
        Double price;
        while (true) {
            try {
                price = Double.parseDouble(input.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect price. Please enter it again:");
            }
        }
        return price;
    }


    String getAnimalNick() {
        System.out.println("Enter animal nick:");
        String ret;
        while(true) {
            ret = input.nextLine();
            if (ret != null && !"".equals(ret)) {
                break;
            }
            System.out.println("Incorrect nick. Please enter again:");

        }
        return ret;

    }
    AnimalSex getSex() {
        System.out.println("Enter animal sex (M or F):");
        String ret;
        while(true) {
            ret = input.nextLine();
            boolean isCorrect = ret != null && ("m".equals(ret.toLowerCase()) || "f".equals(ret.toLowerCase()));
            if (isCorrect && !"".equals(ret)) {
                break;
            }
            System.out.println("Incorrect sex. Please enter again:");

        }
        return AnimalSex.getByCode(ret.toLowerCase());

    }

    private void printListOfAnimals() {
        shop.getShopAnimals().forEach(System.out::println);

    }

    private void printListOfSales() {
        List<ChangeItem> sales = shop.getChangesHistory()
                .getChangeItems().stream()
                .filter(change -> change instanceof SaleItem)
                .collect(Collectors.toList());
        sales.forEach(System.out::println);

    }

    private void printListOfReceives() {
        List<ChangeItem> sales = shop.getChangesHistory()
                .getChangeItems().stream()
                .filter(change -> change instanceof ReceiveItem)
                .collect(Collectors.toList());
        sales.forEach(System.out::println);

    }

    private void printListOfTransactions() {
        shop.getChangesHistory().getChangeItems().forEach(System.out::println);

    }

    private void printListOfCustomers() {
        shop.getShopCustomers().values()
                .forEach(customer -> {
                    System.out.println(customer);
                        double accum = 0;
                        for(Animal animal : shop.getShopAnimals()) {
                            if (animal.getOwner() == customer) {
                                System.out.println(animal.getNick() + "(" + animal.getSpecies().getName() +
                                                ", " + animal.getPrice().getAmount() + ")");
                                accum += animal.getPrice().getAmount();
                            }
                        }
                    System.out.println("Total spent: " + accum);

        });


    }

    int getAdminCommand() {
        System.out.println("Choose command from list: \n1 \t- \tlist of animals; \n2 \t- \tadd a new animal; " +
                "\n3 \t- \tlist of sales; \n4 \t- \tlist of new additions; \n5 \t- \tlist of transactions; " +
                "\n6 \t- \tlist of customers" +
                "(press Enter to quit):");
        while(true) {
            String next = input.nextLine();
            if ("".equals(next)) {
                break;
            }
            try {
                return Integer.parseInt(next);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe ) {
                System.out.println("Incorrect index of allergy. Please try again:");
            }
        }
        return -1;
    }






}
