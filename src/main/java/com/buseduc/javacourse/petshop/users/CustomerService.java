package com.buseduc.javacourse.petshop.users;

import com.buseduc.javacourse.petshop.Animal;
import com.buseduc.javacourse.petshop.Currency;
import com.buseduc.javacourse.petshop.Petshop;
import com.buseduc.javacourse.petshop.animalproperties.Allergable;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerService {
    private Scanner input;
    private Petshop shop;
    private AdminService adminService;

    public CustomerService(Petshop shop) {
        this.shop = shop;
        this.input = new Scanner(System.in);
        this.adminService = AdminService.getInstance(this.shop);
    }

    public void start() {
        User user = null;
        while(true) {
            if (user == null) {
                String name = getCustomerName();
                user = loginOrRegister(name);
            }
            if (user instanceof Customer) {
                if (!handleCustomer((Customer) user)) {
                    user = null;
                }
            } else {
                if (!adminService.handleAdmin((Admin) user)) {
                    user = null;
                }
            }

        }
    }

    private boolean handleCustomer(Customer customer) {
        shop.getShopCustomers().put(customer.getName(), customer);
        System.out.println(shop.getShopCustomers());
        int animalsCount = printAnimalsList(customer);
        if (animalsCount > 0) {
            if (!askCustomerToChooseAnimal(customer)) {
                System.out.println("Good bye!");
                return false;
            }
        } else {
            System.out.println("Sorry, we have no animals for your requirements. Good bye!");
            return false;
        }
        return true;
    }

    private boolean askCustomerToChooseAnimal(Customer customer) {
        int animalId;
        System.out.println("Please choose animal (type animal id):");
        while (true) {
            String inputStr = "";
            try {
                inputStr = input.nextLine();
                animalId = Integer.parseInt(inputStr);
                break;
            } catch (NumberFormatException nfe) {
                if("q".equals(inputStr.toLowerCase())) {
                    return false;
                }
                System.out.println("Incorrect id. Please enter it again:");
            }
        }
        Animal toBuy = shop.findAnimalById(animalId);
        if (toBuy != null && customer.payForAnimal(toBuy)) {
            System.out.println("Thank you for your choice!");
            shop.setBalance(shop.getBalance() - toBuy.getPrice().getAmount());
            shop.getChangesHistory().update(toBuy, shop.getBalance(), true);
            toBuy.setOwner(customer);
        } else {
            System.out.println("Deal failed");
        }
        System.out.println(customer);
        System.out.println(shop.getShopAnimals());
        return true;

    }

    private boolean isAvailableAnimal(Animal animal, Double availableMoney, Allergy allergy) {
        boolean isAllergyFound = false;
        if(animal.getSpecies() != null && animal.getSpecies() instanceof Allergable) {
            Set<Allergy> allergies = ((Allergable) animal.getSpecies()).produceAllergy();
            isAllergyFound = allergies.contains(allergy);
        }
        return animal.getOwner() == null &&
                !isAllergyFound &&
                animal.getPrice().getAmount() <= availableMoney;
    }

    public int printAnimalsList(Customer customer) {
        System.out.println("Here are the animals you can buy:");
        System.out.println("__________________________________");
        List<Animal> availableAnimals = shop.getShopAnimals().stream()
                .filter(animal -> {
                    boolean isToPrint = isAvailableAnimal(animal, customer.getAvailableMoney(), customer.getAllergy());
                    if (isToPrint) {
                        System.out.println(
                                animal.getId() +  " - \t" +
                                        animal.getSpecies().getName() + " - \t" +
                                        animal.getNick() +
                                        " \t (" + animal.getPrice().toString() + ")");
                    }
                    return isToPrint;
                }).collect(Collectors.toList());
        System.out.println("__________________________________");
        return availableAnimals.size();

    }

    public User loginOrRegisterFromWeb(String name, Double amount, Integer age) {
        if ("admin".equals(name)) {
            return new Admin();
        }
        if (shop.getShopCustomers().containsKey(name)) {
            System.out.println("Hello again, " + name);
            return shop.getShopCustomers().get(name);
        }

        Allergy allergy = null;
        Customer customer = new Customer(name, amount, Currency.EUR, age, allergy);
        shop.getShopCustomers().put(customer.getName(), customer);
        return customer;
    }
    public User loginOrRegister(String name) {
        if ("admin".equals(name)) {
            return new Admin();
        }
        if (shop.getShopCustomers().containsKey(name)) {
            System.out.println("Hello again, " + name);
            return shop.getShopCustomers().get(name);
        }

        Double amount = getAmount();
        Integer age = getCustomerAge();
        Allergy allergy = getCustomerAllergy();
        System.out.println("Welcome, " + name);
        return new Customer(name, amount, Currency.EUR, age, allergy);
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
    Allergy getCustomerAllergy() {
        System.out.println("Choose allergy (press Enter if you don't have any):");
        Allergy ret = null;
        Arrays.stream(Allergy.values())
                .forEach(allergy -> System.out.println(allergy.ordinal() + " - " + allergy.name()));
        while(true) {
            String next = input.nextLine();
            if ("".equals(next)) {
                break;
            }
            try {
                Integer index = Integer.parseInt(next);
                ret = Allergy.values()[index];
                return ret;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe ) {
                System.out.println("Incorrect index of allergy. Please try again:");
            }
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

    public void printAllList(Collection<?> objectsToPrint) {
        objectsToPrint.forEach(System.out::println);
    }

}
