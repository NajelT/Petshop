package com.buseduc.javacourse.petshop.users;

import com.buseduc.javacourse.petshop.Petshop;

import java.util.Scanner;

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

    void handleAdmin(Admin admin) {

    }



}
