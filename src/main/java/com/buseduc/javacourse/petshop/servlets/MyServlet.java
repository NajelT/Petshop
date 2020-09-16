package com.buseduc.javacourse.petshop.servlets;

import com.buseduc.javacourse.petshop.Animal;
import com.buseduc.javacourse.petshop.Petshop;
import com.buseduc.javacourse.petshop.Settings;
import com.buseduc.javacourse.petshop.users.AdminService;
import com.buseduc.javacourse.petshop.users.CustomerService;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {
    private static Petshop shop;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Petshop petshop = getShopInstance();
        petshop.createAnimals();
        ServletContext ctx = getServletContext();
        request.setAttribute("petshop", petshop);
        AdminService service = AdminService.getInstance(petshop);
        RequestDispatcher dispatcher = ctx.getRequestDispatcher("/jsp/shop.jsp");
        dispatcher.forward(request, response);

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Petshop shop = getShopInstance();
        CustomerService custService = new CustomerService(shop);
        PrintWriter out = response.getWriter();
        response.setContentType("text/html; charset=UTF-8");
        request.getParameterMap();
        request.setAttribute("petshop", shop);
        String firstName = request.getParameter("customerName");
        Double amount = NumberUtils.isParsable(request.getParameter("amount")) ?
                Double.parseDouble(request.getParameter("amount")) : null;
        Integer age = NumberUtils.isParsable(request.getParameter("age")) ?
                Integer.parseInt(request.getParameter("age")) : null;
        custService.loginOrRegisterFromWeb(firstName, amount, age);
        request.setAttribute("customerList", shop.getShopCustomers());
        ServletContext ctx = getServletContext();
        RequestDispatcher dispatcher = ctx.getRequestDispatcher("/jsp/shop.jsp");
        dispatcher.forward(request, response);


    }
    private Petshop getShopInstance() {
        if (shop == null) {
            Settings settings = Settings.getInstance();
            shop = Petshop.getInstance(settings);
        }
        return shop;
    }
}