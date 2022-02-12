package com.buseduc.javacourse.petshop.servlets;

import com.buseduc.javacourse.petshop.Petshop;
import com.buseduc.javacourse.petshop.animalproperties.Allergy;
import com.buseduc.javacourse.petshop.db.hibernate.HibernateConfig;
import com.buseduc.javacourse.petshop.users.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //Notification that the servlet context is about to be shut down.
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
/*
        System.out.println("Trying to obtain DB connection");
        JdbcAgent dbAgent = JdbcAgent.getInstance();
        dbAgent.connect();
        Petshop.getInstance().setShopCustomers(dbAgent.getCustomers());
        System.out.println(dbAgent.getCustomers());
*/
        HibernateConfig cfg = HibernateConfig.getInstance();
        cfg.doConfig();
        SessionFactory factoryObj = cfg.getFactoryObj();
        Session sessionObj = factoryObj.openSession(); // Session Object
        CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> cr = criteria.from(Customer.class);
        criteria.select(cr);
        Query<Customer> query = sessionObj.createQuery(criteria);
        List<Customer> customers = query.getResultList();
        Map<String, Customer> customerMap = customers.stream().collect(Collectors.toMap(Customer::getName, c -> c));
        Petshop.getInstance().setShopCustomers(customerMap);
        sessionObj.close();
        System.out.println("Client Data Successfully Saved In Database!");

    }


}